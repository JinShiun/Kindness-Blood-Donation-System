/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import ADT.*;
import Entity.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Lee Jin Shiun
 */

public class RequestManagement {

    private static PriorityQueueInterface<Request> requestQueue = new PriorityQueue<Request>();
    private ArrayStackInterface<Request> recycleBinStack = new ArrayStack<>();
    BloodBank bb = new BloodBank();
    private Scanner scan = new Scanner(System.in);

    //get current date for the request date
    String requestDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    //change the console color
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void checkQueue() {
        
        //"r" for request
        Request r1 = new Request("R0001", new Donee("D0001"), "A", 10, "03-08-2021", "Pending", 1);
        Request r2 = new Request("R0002", new Donee("D0002"), "A", 20, "04-08-2021", "Pending", 1);
        Request r3 = new Request("R0003", new Donee("D0002"), "B", 30, "05-08-2021", "Pending", 5);
        Request r4 = new Request("R0004", new Donee("D0002"), "O", 40, "06-08-2021", "Pending", 1);
        Request r5 = new Request("R0005", new Donee("D0003"), "AB", 50, "07-08-2021", "Pending", 1);
        

        if (requestQueue.isEmpty()) {
            requestQueue.enqueue(r1);
            requestQueue.enqueue(r2);
            requestQueue.enqueue(r3);
            requestQueue.enqueue(r4);
            requestQueue.enqueue(r5);
        }
    }

    public void requestMainMenu() {
        int option = 0;
        
        System.out.println("=====================================================");
        System.out.println("               Blood Request Management              ");
        System.out.println("=====================================================");
        System.out.println(" 1. Display request");
        System.out.println(" 2. Add new request");
        System.out.println(" 3. Update request");
        System.out.println(" 4. Remove request");
        System.out.println(" 5. Search request");
        System.out.println(" 6. Generate report");
        System.out.println(" 7. Back to main menu");

        try {
            System.out.print("\nChoose an option (1 - 6): ");
            option = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (option) {
            case 1:
                displayRequest();
                break;
            case 2:
                addRequest();
                break;
            case 3:
                chooseRequest();
                break;
            case 4:
                removeMenu();
                break;
            case 5:
                searchMenu();
                break;
            case 6:
                reportMenu();
                break;
            case 7:
                Main.mainMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option selection! Please enter again (1 - 6)" + ANSI_RESET);
                requestMainMenu();
                break;
        }
    }

    private void requestMenuHeader() {
        System.out.println("***********************************************************************************************************************************");
        System.out.println("|No. |Request ID      |Donee ID       |Blood Group      |Request Date     |Request Amount      |Request Status     |Priority Level     |");
        System.out.println("***********************************************************************************************************************************");
    }

    private void addRequest() {
        int requestQty = 0;
        int priorityLvl = 0;
        String bloodGroup = "";
        String confirm = "";
        
        do {
            int requestNum = requestQueue.getTotalEntry() + 1;
            String doneeId = "";
            
            String requestId = String.format("R" + "%04d", requestNum);
            System.out.println("\n\n\n****************************************");
            System.out.println("||            Add Request             ||");
            System.out.println("****************************************");
            System.out.print("Enter Request ID: " + requestId);//request id will auto-increment the last id used

            do {
                System.out.print("\nEnter Donee ID: ");//verify donee id exist or not, print error msg when id not exist 
                doneeId = scan.nextLine();
                if (!DoneeManagement.getValidDonee(doneeId)) {
                    System.out.println(ANSI_RED + "The Donee id was not found!" + ANSI_RESET);
                }
            } while (!DoneeManagement.getValidDonee(doneeId));
            
            //using current or local date
            System.out.println("Request Date: " + requestDate);
            
            //validation of blood Group
            boolean question1 = true;
            do {
                System.out.print("Enter Blood Group [A, B, O, AB]: ");
                bloodGroup = scan.nextLine();
                if (!bloodGroup.equalsIgnoreCase("A") && !bloodGroup.equalsIgnoreCase("B") && !bloodGroup.equalsIgnoreCase("AB") && !bloodGroup.equalsIgnoreCase("O")) {
                    System.out.println(ANSI_RED + "Invalid input ! Please enter again [A, B, O, AB].\n" + ANSI_RESET);
                    question1 = true;
                } else {
                    question1 = false;
                }
            } while (question1);

            //validation of blood bag request quantity
            boolean question2 = true;
            do {
                try {
                    System.out.print("Enter Request Quantity of Blood Bag [300 ml per bag]: ");
                    requestQty = scan.nextInt();
                    question2 = false;
                } catch (Exception e) {
                    System.out.println(ANSI_RED + "Invalid input ! Please enter again.[300 ml per bag]\n" + ANSI_RESET);
                    scan.nextLine();
                }
            } while (question2);

            //The status of the new request will be pending. 
            System.out.print("Request Status : Pending\n");
            String status = "Pending";

            //validation of priority level
            boolean question3 = true;
            do {
                try {
                    //larger number will have higher priority
                    System.out.print("Enter Priority Level [5 - 1]: ");
                    priorityLvl = scan.nextInt();
                    scan.nextLine();

                    if (priorityLvl < 1 || priorityLvl > 5) {
                        System.out.println(ANSI_RED + "Priority level between 1 to 5 only.\n" + ANSI_RESET);
                    } else {
                        question3 = false;
                    }
                } catch (Exception e) {
                    System.out.println(ANSI_RED + "Invalid input ! Please enter again [5 - 1].\n" + ANSI_RESET);
                    scan.nextLine();
                }
            } while (question3);

            //Continue adding or stop adding
            boolean question4 = true;
            do {
                System.out.print("Continue adding new request ? [Y - Yes/N - No]: ");
                confirm = scan.nextLine();
                if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("Yes")) {
                    requestQueue.enqueue(new Request(requestId, new Donee(doneeId), bloodGroup.toUpperCase(), requestQty, requestDate, status, priorityLvl));
                    System.out.println(ANSI_GREEN + "\nSuccessfully add a new request." + ANSI_RESET + "\n\n");
                    question4 = false;
                } else if (confirm.equalsIgnoreCase("N") || confirm.equalsIgnoreCase("No")) {
                    requestQueue.enqueue(new Request(requestId, new Donee(doneeId), bloodGroup.toUpperCase(), requestQty, requestDate, status, priorityLvl));
                    System.out.println("\n" + ANSI_GREEN + "All request had been successfully added !\n\n" + ANSI_RESET);
                    question4 = false;
                } else {
                    System.out.println(ANSI_RED + "\nInvalid input ! Please enter again [Y - Yes/N - No]." + ANSI_RESET);
                    question4 = true;
                }
            } while (question4);
        } while (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("Yes"));
        requestMainMenu();
    }

    private void displayRequest() {
        System.out.println("\n\n****************************************");
        System.out.println("||          Display Request           ||");
        System.out.println("****************************************");
        System.out.println("1. Display pending request list");
        System.out.println("2. Display existing request record");
        System.out.println("3. Back");

        
        int option = 0;

        try {
            System.out.print("\nEnter your option: ");
            option = scan.nextInt();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (option) {
            case 1:
                System.out.println("\n\nPending Request: ");
                requestMenuHeader();
                if (!requestQueue.isEmpty()) {
                    for (int i = 1; i <= requestQueue.getNumEntry(); i++) {
                        System.out.println(" " + i + ". " + requestQueue.getEntry(i));
                    }
                } else {
                    System.out.println(ANSI_RED + "No record found" + ANSI_RESET);
                }
                //Blue font color for confirmation
                System.out.println(ANSI_BLUE + "Press 'Enter' to back to menu.\n\n" + ANSI_RESET);
                scan.nextLine();
                scan.nextLine();
                displayRequest();
                break;
            case 2:
                System.out.println("\n\nExisting Request Record: ");
                requestMenuHeader();
                BloodBankInventory.displayReviewRequest();
                System.out.println(ANSI_BLUE + "Press 'Enter' to back to menu.\n\n" + ANSI_RESET);
                scan.nextLine();
                scan.nextLine();
                displayRequest();
                break;
            case 3:
                requestMainMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option ! Please enter again." + ANSI_RESET);
                displayRequest();
                break;
        }
    }

    private void chooseRequest() {
        int selection = 0;

        //display all pending request
        System.out.println("\n\nPending Request: ");
        requestMenuHeader();
        for (int i = 1; i <= requestQueue.getNumEntry(); i++) {
            System.out.println(" " + i + ". " + requestQueue.getEntry(i));
        }

        //Verify the selected request
        try {
            System.out.print("Choose request to update or [exit - 0] : ");
            selection = scan.nextInt();
        } catch (Exception e) {
            scan.nextLine();
        }

        //Verify that the selected request exists
        if (selection > 0 && selection <= requestQueue.getNumEntry()) {
            updateRequest(selection);
        }else if(selection == 0){
            requestMainMenu();
        } else {
            System.out.println(ANSI_RED + "No record found, request not exist." + ANSI_RESET);
            chooseRequest();
        }
    }

    private void updateRequest(int selection) {
        System.out.println("\n\nRequest to update: ");
        requestMenuHeader();
        Request requestChoose = (Request) requestQueue.getEntry(selection);
        System.out.println(" 1." + requestChoose);

        System.out.println("\n\n*****************************************");
        System.out.println("||           Update Request            ||");
        System.out.println("*****************************************");
        System.out.println("1. Blood Group");
        System.out.println("2. Quantity of Blood Bags");
        System.out.println("3. Priority Level");
        System.out.println("4. Request Status");
        System.out.println("5. Back");

        int valueToUpdate = 0;

        try {
            System.out.print("\nPlease select one to update (1 - 5): ");
            valueToUpdate = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (valueToUpdate) {
            case 1:
                boolean question5 = true;
                do {
                    System.out.print("\nEnter new blood group [A, B, O, AB]: ");
                    String newBloodGroup = scan.nextLine().toUpperCase();
                    if (!newBloodGroup.equals("A") && !newBloodGroup.equals("B") && !newBloodGroup.equals("AB") && !newBloodGroup.equals("O")) {
                        System.out.println(ANSI_RED + "Invalid blood type ! Please re-enter.\n" + ANSI_RESET);
                        question5 = true;
                    } else {
                        requestQueue.replace(selection, new Request(requestChoose.getRequestId(),
                                requestChoose.getDoneeId(), newBloodGroup, requestChoose.getRequestQty(),
                                requestChoose.getRequestDate(), requestChoose.getStatus(), requestChoose.getPriorityLvl()));
                        System.out.println(ANSI_GREEN + "Successfully update blood type !\n" + ANSI_RESET);
                        question5 = false;
                    }
                } while (question5);
                break;
            case 2:
                boolean question6 = true;
                do {
                    try {
                        System.out.print("Enter new request quantity of Blood Bag [300 ml per bag]: ");
                        int newRequestQty = scan.nextInt();
                        requestQueue.replace(selection, new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), requestChoose.getBloodGroup(), newRequestQty, requestChoose.getRequestDate(), requestChoose.getStatus(), requestChoose.getPriorityLvl()));
                        System.out.println(ANSI_GREEN + "Successfully update request quantity of blood bag !\n" + ANSI_RESET);
                        question6 = false;
                    } catch (Exception e) {
                        System.out.println(ANSI_RED + "Invalid input ! Please enter again [300 ml per bag].\n" + ANSI_RESET);
                        scan.nextLine();
                    }
                } while (question6);
                break;
            case 3:
                boolean question7 = true;
                do {
                    try {
                        System.out.print("Enter Priority Level [5 - 1]: ");
                        int newPriorityLvl = scan.nextInt();
                        requestQueue.remove(valueToUpdate);
                        requestQueue.enqueue(new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), requestChoose.getBloodGroup(), requestChoose.getRequestQty(), requestChoose.getRequestDate(), requestChoose.getStatus(), newPriorityLvl));
                        System.out.println(ANSI_GREEN + "Successfully update priority level !\n" + ANSI_RESET);
                        if (newPriorityLvl < 1 || newPriorityLvl > 5) {
                            System.out.println(ANSI_RED + "Priority level between 1 to 5 only.\n" + ANSI_RESET);
                        } else {
                            question7 = false;
                        }
                    } catch (Exception e) {
                        System.out.println(ANSI_RED + "Invalid input ! Please enter again [5 - 1].\n" + ANSI_RESET);
                        scan.nextLine();
                    }
                } while (question7);
                break;
            case 4:
                boolean question8 = true;
                do {
                    System.out.println(ANSI_BLUE + "Once the request is updated, the request will be removed from the pending queue." + ANSI_RESET);
                    System.out.print("Enter new request status [Approved - A/Rejected - R]: ");
                    String newStatus = scan.nextLine();
                    if (newStatus.equalsIgnoreCase("A") || newStatus.equalsIgnoreCase("Approved")) {
                        String statusApprove = "Approved";
                        if (!BloodBankInventory.updBloodRequest(requestChoose.getBloodGroup(), requestChoose.getRequestQty())) {
                            System.out.println(ANSI_RED + "Blood inventory no enough. Unable to make changes !" + ANSI_RESET);
                        } else {
                            Request r = new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), requestChoose.getBloodGroup(), requestChoose.getRequestQty(), requestChoose.getRequestDate(), statusApprove, requestChoose.getPriorityLvl());
                            BloodBankInventory.addToBloodBank(r);
                            requestQueue.remove(selection);
                        }
                        question8 = false;
                    } else if (newStatus.equalsIgnoreCase("R") || newStatus.equalsIgnoreCase("Rejected")) {
                        String statusReject = "Rejected";
                        bb.addRequest(new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), requestChoose.getBloodGroup(), requestChoose.getRequestQty(), requestChoose.getRequestDate(), statusReject, requestChoose.getPriorityLvl()));
                        requestQueue.remove(selection);
                        question8 = false;
                    } else {
                        System.out.println(ANSI_RED + "\nInvalid option !" + ANSI_RESET);
                        question8 = true;
                    }
                } while (question8);
                break;
            case 5:
                requestMainMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option selection! Please enter again (1 - 5)" + ANSI_RESET);
                updateRequest(selection);
                break;
        }
        chooseRequest();
    }

    private void removeMenu() {
        System.out.println("\n\n*****************************************");
        System.out.println("||           Remove Request            ||");
        System.out.println("*****************************************");
        System.out.println("1. Remove pending request");
        System.out.println("2. View recycle bin");
        System.out.println("3. Remove all request");
        System.out.println("4. Back");

        int optionDelete = 0;
        try {
            System.out.print("Choose an option [1 - 3]: ");
            optionDelete = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (optionDelete) {
            case 1:
                removeRequest();
                break;
            case 2:
                viewRecycleBin();
                break;
            case 3:
                removeAll();
                break;
            case 4:
                requestMainMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option ! Please enter again [1 - 3]." + ANSI_RESET);
                removeMenu();
                break;
        }
    }

    private void removeRequest() {
        System.out.println("Pending request: ");
        requestMenuHeader();
        for (int i = 1; i <= requestQueue.getNumEntry(); i++) {
            System.out.println(" " + i + ". " + requestQueue.getEntry(i));
        }

        System.out.print("Choose request to remove: ");
        int requestDelete = scan.nextInt();
        System.out.println(requestQueue.getEntry(requestDelete));
        
        String confirmDelete = "";
        boolean question9;
        do {
            System.out.println("Confirm remove this request ? (Yes - Y/No - N): ");
            confirmDelete = scan.nextLine();
            if (confirmDelete.equalsIgnoreCase("Yes") || confirmDelete.equalsIgnoreCase("Y")) {
                recycleBinStack.push(requestQueue.getEntry(requestDelete));
                requestQueue.remove(requestDelete);
                System.out.println(ANSI_GREEN + "Request successfully remove from queue !" + ANSI_RESET);
                question9 = false;
            } else if (confirmDelete.equalsIgnoreCase("No") || confirmDelete.equalsIgnoreCase("N")) {
                System.out.println(ANSI_BLUE + "No changes make !" + ANSI_RESET);
                question9 = false;
            } else {
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                question9 = true;
            }
        } while (question9);
        removeMenu();
    }

    private void viewRecycleBin() {
        System.out.println("Deleted request: ");
        requestMenuHeader();
        int top = recycleBinStack.getNumOfEntry();
        int i = 1;
        for (int j = top; j >= 0; j--) {
            System.out.println(" " + i + ". " + recycleBinStack.peek(j));
            i++;
        }
        boolean question10 = true;
        do {
            System.out.println(ANSI_BLUE + "Enter [Clear - C] to clear the bin, [Back - B] back to menu, [Restore - R] to restore request." + ANSI_RESET);
            String clear = scan.nextLine();
            if (clear.equalsIgnoreCase("Clear") || clear.equalsIgnoreCase("C")) {
                question10 = false;
                recycleBinStack.clear();
                System.out.println(ANSI_GREEN + "The recycle bin had been clear. Press 'Enter' back to menu." + ANSI_RESET);
                scan.nextLine();
                removeMenu();
            } else if (clear.equalsIgnoreCase("Back") || clear.equalsIgnoreCase("B")) {
                question10 = false;
                removeMenu();
            } else if (clear.equalsIgnoreCase("Restore") || clear.equalsIgnoreCase("R")) {
                question10 = false;
                System.out.print("Enter request record to restore: ");
                int restoreRequest = scan.nextInt();
                scan.nextLine();
                int findRequest = top + 1 - restoreRequest;
                System.out.println(recycleBinStack.peek(findRequest));
                System.out.println(ANSI_RED + "Request record will restore as new record!" + ANSI_RESET);
                System.out.print("Confirm restore this record ? (Yes - Y/No - N): ");
                String confirmDelete = scan.nextLine();
                if (confirmDelete.equalsIgnoreCase("Yes") || confirmDelete.equalsIgnoreCase("Y")) {
                    requestQueue.enqueue(recycleBinStack.peek(findRequest));
                    recycleBinStack.pop(findRequest);
                    System.out.println(ANSI_GREEN + "Request successfully restore from recycle bin !" + ANSI_RESET);
                } else if (confirmDelete.equalsIgnoreCase("No") || confirmDelete.equalsIgnoreCase("N")) {
                    System.out.println(ANSI_BLUE + "No changes make !" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Invalid option !\n\n" + ANSI_RESET);
            }
        } while (question10);
        removeMenu();
    }

    private void removeAll() {
        System.out.println("\n\n" + ANSI_RED + "This function will remove all request !\n" + ANSI_RESET);
        boolean question1 = true;
        do {
            System.out.print("Confirm delete all request ? [Yes - Y/No - N]: ");
            String confirm = scan.nextLine();
            if (confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("Y")) {
                question1 = false;
                do {
                    recycleBinStack.push(requestQueue.dequeue());
                } while (requestQueue.getNumEntry() > 0);
                System.out.println(ANSI_GREEN + "All request deleted successfully." + ANSI_RESET);
                removeMenu();
            } else if (confirm.equalsIgnoreCase("No") || confirm.equalsIgnoreCase("N")) {
                question1 = false;
                System.out.println(ANSI_BLUE + "No changes make !" + ANSI_RESET);
                removeMenu();
            } else {
                question1 = true;
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
            }
        } while (question1);
    }

    private void searchMenu() {
        System.out.println("\n\n*****************************************");
        System.out.println("||           Search Request            ||");
        System.out.println("*****************************************");
        System.out.println("1. Blood Group");
        System.out.println("2. Request Quantity Blood Bag");
        System.out.println("3. Priority Level");
        System.out.println("4. Back");

        int searchOption = 0;
        try {
            System.out.print("Search request by: ");
            searchOption = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (searchOption) {
            case 1:
                searchBloodGroup();
                break;
            case 2:
                searchBloodQty();
                break;
            case 3:
                searchPriorityLevel();
                break;
            case 4:
                requestMainMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option ! Please enter again [1-4]." + ANSI_RESET);
                searchMenu();
                break;
        }
    }

    private void searchBloodGroup() {
        boolean question5 = true;
        do {
            System.out.print("\nEnter blood group [A, B, O, AB]: ");
            String bloodGroup = scan.nextLine().toUpperCase();
            if (!bloodGroup.equals("A") && !bloodGroup.equals("B") && !bloodGroup.equals("AB") && !bloodGroup.equals("O")) {
                System.out.println(ANSI_RED + "Invalid blood group ! Please enter again [A, B, O, AB].\n" + ANSI_RESET);
                question5 = true;
            } else {
                requestMenuHeader();
                int j = 1;
                for (int i = 1; i <= requestQueue.getNumEntry(); i++) {
                    Request selectedRequest = requestQueue.getEntry(i);
                    if (bloodGroup.equals(selectedRequest.getBloodGroup())) {
                        System.out.println(j + ". " + selectedRequest);
                        j++;
                    }
                }
                question5 = false;
            }
        } while (question5);
        searchMenu();
    }

    private void searchBloodQty() {
        boolean question6 = true;
        do {
            try {
                System.out.print("Search request by quantity of Blood bag from: ");
                int qtyFrom = scan.nextInt();
                System.out.print("Search request by quantity of Blood bag to: ");
                int qtyTo = scan.nextInt();
                requestMenuHeader();
                int j = 1;
                for (int i = 1; i <= requestQueue.getNumEntry(); i++) {
                    Request selectedRequest = requestQueue.getEntry(i);
                    if (qtyFrom <= selectedRequest.getRequestQty() && selectedRequest.getRequestQty() <= qtyTo) {
                        System.out.println(j + ". " + selectedRequest);
                        j++;
                    }
                }
                question6 = false;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Invalid request amount ! Please re-enter.\n" + ANSI_RESET);
                scan.nextLine();
            }
        } while (question6);
        searchMenu();
    }

    private void searchPriorityLevel() {
        boolean question7 = true;
        do {
            try {
                System.out.print("Enter Need Level [5 - 1]: ");//larger number will have higher priority
                int PriorityLvl = scan.nextInt();
                if (PriorityLvl < 1 || PriorityLvl > 5) {
                    System.out.println(ANSI_RED + "Need level must between 1 to 5\n" + ANSI_RESET);
                } else {
                    requestMenuHeader();
                    int j = 1;
                    for (int i = 1; i <= requestQueue.getNumEntry(); i++) {
                        Request selectedRequest = requestQueue.getEntry(i);
                        if (PriorityLvl == selectedRequest.getPriorityLvl()) {
                            System.out.println(j + ". " + selectedRequest);
                            j++;
                        }
                    }
                    question7 = false;
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Invalid need level ! Please re-enter.\n" + ANSI_RESET);
                scan.nextLine();
            }
        } while (question7);
        searchMenu();
    }

    public void reportMenu() {
        System.out.println("\n\n*****************************************");
        System.out.println("||           Generate Report            ||");
        System.out.println("*****************************************");
        System.out.println("1. Pending Request Report");
        System.out.println("2. Reviewed Request Report");
        System.out.println("3. Back");

        int reportOption = 0;
        try {
            System.out.print("\nReport to generate: ");
            reportOption = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (reportOption) {
            case 1:
                pendingRequestReport();
                break;
            case 2:
                BloodBankInventory.reviewedRequestReport();
                reportMenu();
                break;
            case 3:
                requestMainMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                reportMenu();
                break;
        }
    }

    public void pendingRequestReport() {
        if (requestQueue.isEmpty()) {
            System.out.println("The pending request is empty, no report generated!");
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/Pending_Request[" + requestDate + "].txt", false));
                writer.write("-----------------------------------------------------------------------------------------------------------\n");
                writer.write("\t\t\t\t\tPending request Report\n");
                writer.write("-----------------------------------------------------------------------------------------------------------\n");
                writer.write("Request ID\tDonee ID\tBlood Type\tRequest Date\tAmount\t\tStatus\t\tNeed Level\n");
                writer.write("-----------------------------------------------------------------------------------------------------------\n");
                writer.close();
            } catch (Exception e) {
                System.out.println("\n\n");
            }
            for (int i = 0; i <= requestQueue.getNumEntry(); i++) {
                Request reportData = requestQueue.getEntry(i);
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/Pending_Request[" + requestDate + "].txt", true));
                    writer.append(reportData.getRequestId() + "\t\t" + reportData.getDoneeId().toString2() + "\t\t"
                            + reportData.getBloodGroup() + "\t\t" + reportData.getRequestDate() + "\t"
                            + reportData.getRequestQty() + "\t\t" + reportData.getStatus() + "\t\t" + reportData.getPriorityLvl() + "\n");
                    writer.close();
                } catch (Exception e) {
                    System.out.println("\n\n");
                }
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Pending_Request[" + requestDate + "].txt", true));
            writer.append("\n------------------------------------------END OF REPORT---------------------------------------------------\n");
            writer.append("\nDate of report generated :" + requestDate);
            writer.append("\nTotal pending request : " + requestQueue.getNumEntry());

            writer.close();
        } catch (Exception e) {
            System.out.println("\n\n");
        }
        System.out.println("Report with file name : Pending_Request[" + requestDate + "] generated!");
        reportMenu();
    }
}
