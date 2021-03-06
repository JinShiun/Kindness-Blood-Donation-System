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
 * @author Alex
 */
public class DonationManagement {
    private static LinkedListInterface<Donation> DonationList = new LinkedList<Donation>();
    private ArrayStackInterface<Donation> recycleBinStack = new ArrayStack<>();
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

        Donation r1 = new Donation("R0001", new Donee("D0001"), "A", 10, "03-08-2021", "Pending", 1);
        Donation r2 = new Donation("R0002", new Donee("D0002"), "A", 20, "04-08-2021", "Pending", 1);
        Donation r3 = new Donation("R0003", new Donee("D0002"), "B", 30, "05-08-2021", "Pending", 5);
        Donation r4 = new Donation("R0004", new Donee("D0002"), "O", 40, "06-08-2021", "Pending", 1);
   

        if (DonationList.isEmpty()) {
            DonationList.add(r1);
            DonationList.add(r2);
            DonationList.add(r3);
            DonationList.add(r4);
        }
    }

    public void DonationMenu() {
        int choosen = 0;
        System.out.println("=====================================================");
        System.out.println("               Blood Donation Management             ");
        System.out.println("=====================================================");
        System.out.println(" 1. Add new request");
        System.out.println(" 2. Display request");
        System.out.println(" 3. Update request");
        System.out.println(" 4. Remove request");
        System.out.println(" 5. Search request");
        System.out.println(" 6. Generate report");
        System.out.println(" 7. Back to main menu");

        try {
            System.out.print("\nChoose a function (1 - 6): ");
            choosen = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (choosen) {
            case 1:
                addRequest();
                break;
            case 2:
                displayRequest();
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
                System.out.println(ANSI_RED + "Invalid function choose !" + ANSI_RESET);
                DonationMenu();
                break;
        }
    }

    private void requestHeader() {
        System.out.println("***********************************************************************************************************************************");
        System.out.println("|No. |Request ID      |Donee ID       |Blood Type      |Request Date     |Request Amount      |Request Status     |Need Level     |");
        System.out.println("***********************************************************************************************************************************");
    }

    private void addRequest() {
        String decision = "";
        String bloodType = "";
        int requestAmount = 0;
        int needLevel = 0;

        do {
            int num = DonationList.getEntry();
            //int num = requestQueue.getNumEntry() + 1;
            String requestId = String.format("R" + "%04d", num);
            System.out.println("\n\n\n****************************************");
            System.out.println("||            Add Request             ||");
            System.out.println("****************************************");
            System.out.print("Enter Request ID: " + requestId);//request id will auto-increment the last id used

            String doneeId = "";
            do {
                System.out.print("\nEnter Donee ID: ");//verify donee id exist or not, print error msg when id not exist 
                doneeId = scan.nextLine();
                if (!DoneeManagement.getValidDonee(doneeId)) {
                    System.out.println(ANSI_RED + "Donee id not found!" + ANSI_RESET);
                }
            } while (!DoneeManagement.getValidDonee(doneeId));

            System.out.println("Request Date: " + requestDate);//using current or local date
            //validation of blood type
            boolean loop = true;
            do {
                System.out.print("Enter Blood Type [A, B, O, AB]: ");//only these four blood type is validate
                bloodType = scan.nextLine();
                if (!bloodType.equalsIgnoreCase("A") && !bloodType.equalsIgnoreCase("B") && !bloodType.equalsIgnoreCase("AB") && !bloodType.equalsIgnoreCase("O")) {
                    System.out.println(ANSI_RED + "Invalid blood type ! Please re-enter.\n" + ANSI_RESET);
                    loop = true;
                } else {
                    loop = false;
                }
            } while (loop);

            //validation of blood bag request amount
            boolean loop2 = true;
            do {
                try {
                    System.out.print("Enter Request Amount of Blood Bag [300 ml per bag]: ");
                    requestAmount = scan.nextInt();

                    loop2 = false;
                } catch (Exception e) {
                    System.out.println(ANSI_RED + "Invalid request amount ! Please re-enter.\n" + ANSI_RESET);
                    scan.nextLine();
                }
            } while (loop2);

            System.out.print("Enter Request Status [Approved/Pending/Rejected]: Pending\n");
            String requestStatus = "Pending";

            //validation of need level
            boolean loop3 = true;
            do {
                try {
                    System.out.print("Enter Need Level [5 - 1]: ");//larger number will have higher priority
                    needLevel = scan.nextInt();
                    scan.nextLine();

                    if (needLevel < 1 || needLevel > 5) {
                        System.out.println(ANSI_RED + "Need level must between 1 to 5\n" + ANSI_RESET);
                    } else {
                        loop3 = false;
                    }
                } catch (Exception e) {
                    System.out.println(ANSI_RED + "Invalid need level ! Please re-enter.\n" + ANSI_RESET);
                    scan.nextLine();
                }
            } while (loop3);

            //check user want to keep adding or stop adding and return to menu 
            boolean loop4 = true;
            do {
                System.out.print("Continue adding request ? [Y - Yes/N - No]: ");
                decision = scan.nextLine();
                if (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")) {
                    DonationList.add(new Donation(requestId, new Donee(doneeId), bloodType.toUpperCase(), requestAmount, requestDate, requestStatus, needLevel));

                    System.out.println(ANSI_GREEN + "\nSuccessfully add a new request." + ANSI_RESET + "\n\n");
                    loop4 = false;
                } else if (decision.equalsIgnoreCase("N") || decision.equalsIgnoreCase("No")) {
                    DonationList.add(new Donation(requestId, new Donee(doneeId), bloodType.toUpperCase(), requestAmount, requestDate, requestStatus, needLevel));
                    System.out.println("\n" + ANSI_GREEN + "All request had been successfully added !\n\n" + ANSI_RESET);
                    loop4 = false;
                } else {
                    System.out.println(ANSI_RED + "\nInvalid option !" + ANSI_RESET);
                    loop4 = true;
                }
            } while (loop4);
        } while (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes"));
        DonationMenu();//return to request management menu
    }

    private void displayRequest() {
        System.out.println("\n\n****************************************");
        System.out.println("||          Display Request           ||");
        System.out.println("****************************************");
        System.out.println("1. Display pending request list");
        System.out.println("2. Display existing request record");
        System.out.println("3. Back");

        boolean loop5 = true;
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
                requestHeader();
                if (!DonationList.isEmpty()) {
                    for (int i = 1; i <= DonationList.getNumberOfEntries(); i++) {
                        System.out.println(" " + i + ". " + DonationList.getEntry(i));
                    }
                } else {
                    System.out.println(ANSI_RED + "No record found" + ANSI_RESET);
                }
                System.out.println(ANSI_BLUE + "Press 'Enter' to back to menu.\n\n" + ANSI_RESET);
                scan.nextLine();
                scan.nextLine();
                displayRequest();
                break;
            case 2:
                System.out.println("\n\nExisting Request Record: ");
                requestHeader();
                BloodBankInventory.displayReviewRequest();
                System.out.println(ANSI_BLUE + "Press 'Enter' to back to menu.\n\n" + ANSI_RESET);
                scan.nextLine();
                scan.nextLine();
                displayRequest();
                break;
            case 3:
                DonationMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                displayRequest();
                break;
        }
    }

    private void chooseRequest() {
        int requestUpd = 0;

        //display all pending request
        System.out.println("\n\nPending Request: ");
        requestHeader();
        for (int i = 1; i <= DonationList.getNumberOfEntries(); i++) {
            System.out.println(" " + i + ". " + DonationList.getEntry(i));
        }

        //validate request choose(int)
        try {
            System.out.print("Choose request to update: ");
            requestUpd = scan.nextInt();
        } catch (Exception e) {
            scan.nextLine();
        }

        //validate did request choose exist
        if (requestUpd > 0 && requestUpd <= DonationList.getNumberOfEntries()) {
            updateRequest(requestUpd);
        } else {
            System.out.println(ANSI_RED + "No record found, request not exist." + ANSI_RESET);
            chooseRequest();
        }
    }

    private void updateRequest(int requestUpd) {
        System.out.println("\n\nRequest to update: ");
        requestHeader();
        Donation donationChoose = (Donation) DonationList.getEntry(requestUpd);
        System.out.println(" 1." + donationChoose);

        System.out.println("\n\n*****************************************");
        System.out.println("||           Update Request            ||");
        System.out.println("*****************************************");
        System.out.println("1. Blood Type");
        System.out.println("2. Request Amount");
        System.out.println("3. Need Level");
        System.out.println("4. Request Status");
        System.out.println("5. Back");

        int infoUpdate = 0;

        try {
            System.out.print("\nWhat you want to update ? (1 - 5): ");
            infoUpdate = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (infoUpdate) {
            case 1:
                boolean loop6 = true;
                do {
                    System.out.print("\nEnter new blood type [A, B, O, AB]: ");//only these four blood type is validate
                    String newBloodType = scan.nextLine().toUpperCase();
                    if (!newBloodType.equals("A") && !newBloodType.equals("B") && !newBloodType.equals("AB") && !newBloodType.equals("O")) {
                        System.out.println(ANSI_RED + "Invalid blood type ! Please re-enter.\n" + ANSI_RESET);
                        loop6 = true;
                    } else {

                        DonationList.replace(requestUpd, new Donation(donationChoose.getDonationId(),
                                donationChoose.getDoneeId(), newBloodType, donationChoose.getRequestAmount(),
                                donationChoose.getRequestDate(), donationChoose.getRequestStatus(), donationChoose.getNeedLevel()));
                        //requestQueue.enqueue(new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), newBloodType, requestChoose.getRequestAmount(), requestChoose.getRequestDate(), requestChoose.getRequestStatus(), requestChoose.getNeedLevel()));
                        //requestQueue.replace(requestUpd - 1, new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), newBloodType, requestChoose.getRequestAmount(), requestChoose.getRequestDate(), requestChoose.getRequestStatus(), requestChoose.getNeedLevel()));
                        System.out.println(ANSI_GREEN + "Successfully update blood type !\n" + ANSI_RESET);
                        loop6 = false;
                    }
                } while (loop6);
                break;
            case 2:
                boolean loop7 = true;
                do {
                    try {
                        System.out.print("Enter new request amount of Blood Bag [300 ml per bag]: ");
                        int newRequestAmount = scan.nextInt();
                        //requestQueue.remove(infoUpdate);
                        //requestQueue.enqueue(new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), requestChoose.getBloodType(), newRequestAmount, requestChoose.getRequestDate(), requestChoose.getRequestStatus(), requestChoose.getNeedLevel()));
                        DonationList.replace(requestUpd, new Donation(donationChoose.getDonationId(), donationChoose.getDoneeId(), donationChoose.getBloodType(), newRequestAmount, donationChoose.getRequestDate(), donationChoose.getRequestStatus(), donationChoose.getNeedLevel()));
                        System.out.println(ANSI_GREEN + "Successfully update request amount !\n" + ANSI_RESET);
                        loop7 = false;
                    } catch (Exception e) {
                        System.out.println(ANSI_RED + "Invalid request amount ! Please re-enter.\n" + ANSI_RESET);
                        scan.nextLine();
                    }
                } while (loop7);

                /*System.out.println("Enter new request amount: ");
                int newRequestAmount = scan.nextInt();
                requestQueue.replace(requestUpd - 1, new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), requestChoose.getBloodType(), newRequestAmount, requestChoose.getRequestDate(), requestChoose.getRequestStatus(), requestChoose.getNeedLevel()));
                System.out.println(ANSI_GREEN + "Successfully update request amount !\n" + ANSI_RESET);*/
                break;
            case 3:
                boolean loop8 = true;
                do {
                    try {
                        System.out.print("Enter Need Level [5 - 1]: ");//larger number will have higher priority
                        int newNeedLevel = scan.nextInt();
                        DonationList.remove(infoUpdate);
                        DonationList.add(new Donation(donationChoose.getDonationId(), donationChoose.getDoneeId(), donationChoose.getBloodType(), donationChoose.getRequestAmount(), donationChoose.getRequestDate(), donationChoose.getRequestStatus(), newNeedLevel));
                        //requestQueue.replace(requestUpd, new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), requestChoose.getBloodType(), requestChoose.getRequestAmount(), requestChoose.getRequestDate(), requestChoose.getRequestStatus(), newNeedLevel));
                        System.out.println(ANSI_GREEN + "Successfully update need level !\n" + ANSI_RESET);
                        if (newNeedLevel < 1 || newNeedLevel > 5) {
                            System.out.println(ANSI_RED + "Need level must between 1 to 5\n" + ANSI_RESET);
                        } else {
                            loop8 = false;
                        }
                    } catch (Exception e) {
                        System.out.println(ANSI_RED + "Invalid need level ! Please re-enter.\n" + ANSI_RESET);
                        scan.nextLine();
                    }
                } while (loop8);

                /*System.out.println("Enter new need level: ");
                int newNeedLevel = scan.nextInt();
                requestQueue.replace(requestUpd - 1, new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), requestChoose.getBloodType(), requestChoose.getRequestAmount(), requestChoose.getRequestDate(), requestChoose.getRequestStatus(), newNeedLevel));
                System.out.println(ANSI_GREEN + "Successfully update need level !\n" + ANSI_RESET);*/
                break;
            case 4:
                boolean loop9 = true;
                do {
                    System.out.println(ANSI_BLUE + "The request will remove from the queue after update." + ANSI_RESET);
                    System.out.print("Enter new request status [Approved - A/Rejected - R]: ");
                    String newRequestStatus = scan.nextLine();
                    if (newRequestStatus.equalsIgnoreCase("A") || newRequestStatus.equalsIgnoreCase("Approved")) {
                        String statusApprove = "Approved";
                        if (!BloodBankInventory.updBloodRequest(donationChoose.getBloodType(), donationChoose.getRequestAmount())) {
                            System.out.println(ANSI_RED + "No changes make. Blood inventory no enough !" + ANSI_RESET);
                        } else {
                            //add to blood bank
//                        BloodBankInventory.updBloodRequest(requestChoose.getBloodType(), requestChoose.getRequestAmount());
//                        System.out.println(BloodBankInventory.updBloodRequest(requestChoose.getBloodType(), requestChoose.getRequestAmount()));
                            Request r = new Request(donationChoose.getDonationId(), donationChoose.getDoneeId(), donationChoose.getBloodType(), donationChoose.getRequestAmount(), donationChoose.getRequestDate(), donationChoose.getNeedLevel(), statusApprove);
                            BloodBankInventory.addToBloodBank(r);
//requestStack.push(new Request(requestChoose.getRequestId(), requestChoose.getDoneeId(), requestChoose.getBloodType(), requestChoose.getRequestAmount(), requestChoose.getRequestDate(), statusApprove, requestChoose.getNeedLevel()));
                            DonationList.remove(requestUpd);
                        }
                        loop9 = false;
                    } else if (newRequestStatus.equalsIgnoreCase("R") || newRequestStatus.equalsIgnoreCase("Rejected")) {
                        String statusReject = "Rejected";
                        bb.addRequest(new Request(donationChoose.getDonationId(), donationChoose.getDoneeId(), donationChoose.getBloodType(), donationChoose.getRequestAmount(), donationChoose.getRequestDate(), donationChoose.getNeedLevel(), statusReject));
                        DonationList.remove(requestUpd);
                        loop9 = false;
                    } else {
                        System.out.println(ANSI_RED + "\nInvalid option !" + ANSI_RESET);
                        loop9 = true;
                    }
                } while (loop9);
                break;
            case 5:
                DonationMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                updateRequest(requestUpd);
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

        int opt = 0;
        try {
            System.out.print("Choose an option: ");
            opt = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (opt) {
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
                DonationMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                removeMenu();
                break;
        }
    }

    private void removeRequest() {
        System.out.println("Pending request: ");
        requestHeader();
        for (int i = 1; i <= DonationList.getNumberOfEntries(); i++) {
            System.out.println(" " + i + ". " + DonationList.getEntry(i));
        }

        System.out.print("Choose request to remove: ");
        int requestDlt = scan.nextInt();
        System.out.println(DonationList.getEntry(requestDlt));
        String confirmDlt = "";
        boolean loop10;
        do {
            System.out.println("Confirm remove this record ? (Yes - Y/No - N): ");
            confirmDlt = scan.nextLine();
            if (confirmDlt.equalsIgnoreCase("Yes") || confirmDlt.equalsIgnoreCase("Y")) {
                recycleBinStack.push(DonationList.getEntry(requestDlt));
                DonationList.remove(requestDlt);
                System.out.println(ANSI_GREEN + "Request successfully remove from queue !" + ANSI_RESET);
                loop10 = false;
            } else if (confirmDlt.equalsIgnoreCase("No") || confirmDlt.equalsIgnoreCase("N")) {
                System.out.println(ANSI_BLUE + "No changes make !" + ANSI_RESET);
                loop10 = false;
            } else {
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                loop10 = true;
            }
        } while (loop10);
        //Request r7 = new Request("R0007", "D0004", "A", 20, "09-08-2021", "Pending", 1);
        //requestQueue.enqueue(r7);
        removeMenu();
    }

    private void viewRecycleBin() {
        System.out.println("Deleted request: ");
        requestHeader();
        int i = 1;
        int top = recycleBinStack.getNumOfEntry();
        for (int j = top; j >= 0; j--) {
            System.out.println(" " + i + ". " + recycleBinStack.peek(j));
            i++;
        }
        boolean loop12 = true;
        do {
            System.out.println(ANSI_BLUE + "Enter [Clear - C] to clear the bin, [Back - B] back to menu, [Restore - R] to restore request." + ANSI_RESET);
            String clear = scan.nextLine();
            if (clear.equalsIgnoreCase("Clear") || clear.equalsIgnoreCase("C")) {
                loop12 = false;
                recycleBinStack.clear();
                System.out.println(ANSI_GREEN + "The recycle bin had been clear. Press 'Enter' back to menu." + ANSI_RESET);
                scan.nextLine();
                removeMenu();
            } else if (clear.equalsIgnoreCase("Back") || clear.equalsIgnoreCase("B")) {
                loop12 = false;
                removeMenu();
            } else if (clear.equalsIgnoreCase("Restore") || clear.equalsIgnoreCase("R")) {
                loop12 = false;
                System.out.print("Enter request record you want to restore: ");
                int restoreRequest = scan.nextInt();
                scan.nextLine();
                int findRequest = top + 1 - restoreRequest;
                System.out.println(recycleBinStack.peek(findRequest));
                System.out.println(ANSI_RED + "Request record will restore as new record!" + ANSI_RESET);
                System.out.print("Confirm restore this record ? (Yes - Y/No - N): ");
                String confirmDlt = scan.nextLine();
                if (confirmDlt.equalsIgnoreCase("Yes") || confirmDlt.equalsIgnoreCase("Y")) {
                    DonationList.add(recycleBinStack.peek(findRequest));
                    //System.out.println(recycleBinStack.peek(restoreRequest));
                    recycleBinStack.pop(findRequest);
                    System.out.println(ANSI_GREEN + "Request successfully restore from recycle bin !" + ANSI_RESET);
                } else if (confirmDlt.equalsIgnoreCase("No") || confirmDlt.equalsIgnoreCase("N")) {
                    System.out.println(ANSI_BLUE + "No changes make !" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Invalid option !\n\n" + ANSI_RESET);
            }
        } while (loop12);
        removeMenu();
    }

    private void removeAll() {
        System.out.println("\n\n" + ANSI_RED + "This function will remove all request !\n" + ANSI_RESET);
        boolean loop = true;
        do {
            System.out.print("Confirm delete all request ? [Yes - Y/No - N]: ");
            String confirm = scan.nextLine();
            if (confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("Y")) {
                loop = false;
                do {
                    recycleBinStack.push(DonationList.remove());
                } while (DonationList.getNumberOfEntries() > 0);
                //requestStack.push();
                //requestQueue.clear();
                System.out.println(ANSI_GREEN + "All request deleted successfully." + ANSI_RESET);
                removeMenu();
            } else if (confirm.equalsIgnoreCase("No") || confirm.equalsIgnoreCase("N")) {
                loop = false;
                System.out.println(ANSI_BLUE + "No changes make !" + ANSI_RESET);
                removeMenu();
            } else {
                loop = true;
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
            }
        } while (loop);
    }

    private void searchMenu() {
        System.out.println("\n\n*****************************************");
        System.out.println("||           Search Request            ||");
        System.out.println("*****************************************");
        System.out.println("1. Blood type");
        System.out.println("2. Request amount");
        System.out.println("3. Need level");
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
                searchByType();
                break;
            case 2:
                searchByAmt();
                break;
            case 3:
                searchByNeedLvl();
                break;
            case 4:
                DonationMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                searchMenu();
                break;
        }
    }

    private void searchByType() {
        boolean loop6 = true;
        do {
            System.out.print("\nEnter blood type [A, B, O, AB]: ");//only these four blood type is validate
            String bloodType = scan.nextLine().toUpperCase();
            if (!bloodType.equals("A") && !bloodType.equals("B") && !bloodType.equals("AB") && !bloodType.equals("O")) {
                System.out.println(ANSI_RED + "Invalid blood type ! Please re-enter.\n" + ANSI_RESET);
                loop6 = true;
            } else {
                requestHeader();
                int j = 1;
                for (int i = 1; i <= DonationList.getNumberOfEntries(); i++) {
                    Donation selectedRequest = DonationList.getEntry(i);
                    if (bloodType.equals(selectedRequest.getBloodType())) {
                        System.out.println(j + ". " + selectedRequest);
                        j++;
                    }
                }
                loop6 = false;
            }
        } while (loop6);
        searchMenu();
    }

    private void searchByAmt() {
        boolean loop7 = true;
        do {
            try {
                System.out.print("Search request by amount from: ");
                int amountFrom = scan.nextInt();
                System.out.print("Search request by amount to: ");
                int amountTo = scan.nextInt();
                requestHeader();
                int j = 1;
                for (int i = 1; i <= DonationList.getNumberOfEntries(); i++) {
                    Donation selectedRequest = DonationList.getEntry(i);
                    if (amountFrom <= selectedRequest.getRequestAmount() && selectedRequest.getRequestAmount() <= amountTo) {
                        System.out.println(j + ". " + selectedRequest);
                        j++;
                    }
                }
                loop7 = false;
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Invalid request amount ! Please re-enter.\n" + ANSI_RESET);
                scan.nextLine();
            }
        } while (loop7);
        searchMenu();
    }

    private void searchByNeedLvl() {
        boolean loop8 = true;
        do {
            try {
                System.out.print("Enter Need Level [5 - 1]: ");//larger number will have higher priority
                int needLevel = scan.nextInt();
                if (needLevel < 1 || needLevel > 5) {
                    System.out.println(ANSI_RED + "Need level must between 1 to 5\n" + ANSI_RESET);
                } else {
                    requestHeader();
                    int j = 1;
                    for (int i = 1; i <= DonationList.getNumberOfEntries(); i++) {
                        Donation selectedRequest = DonationList.getEntry(i);
                        if (needLevel == selectedRequest.getNeedLevel()) {
                            System.out.println(j + ". " + selectedRequest);
                            j++;
                        }
                    }
                    loop8 = false;
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Invalid need level ! Please re-enter.\n" + ANSI_RESET);
                scan.nextLine();
            }
        } while (loop8);
        searchMenu();
    }

    public void reportMenu() {
        System.out.println("\n\n*****************************************");
        System.out.println("||           Generate Report            ||");
        System.out.println("*****************************************");
        System.out.println("1. Pending Request Report");
        System.out.println("2. Reviewed Request Report");
        System.out.println("3. Back");

        int reportChoose = 0;
        try {
            System.out.print("\nReport to generate: ");
            reportChoose = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (reportChoose) {
            case 1:
                pendingRequestReport();
                break;
            case 2:
                BloodBankInventory.reviewedRequestReport();
                reportMenu();
                break;
            case 3:
                DonationMenu();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid option !" + ANSI_RESET);
                reportMenu();
                break;
        }
    }

    public void pendingRequestReport() {
        //check if the transaction array list is empty or not
        if (DonationList.isEmpty()) {
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
            for (int i = 0; i <= DonationList.getNumberOfEntries(); i++) {
                Donation reportData = DonationList.getEntry(i);
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/Pending_Request[" + requestDate + "].txt", true));
                    writer.append(reportData.getDonationId() + "\t\t" + reportData.getDoneeId().toString2() + "\t\t"
                            + reportData.getBloodType() + "\t\t" + reportData.getRequestDate() + "\t"
                            + reportData.getRequestAmount() + "\t\t" + reportData.getRequestStatus() + "\t\t" + reportData.getNeedLevel() + "\n");
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
            writer.append("\nTotal pending request : " + DonationList.getNumberOfEntries());

            writer.close();
        } catch (Exception e) {
            System.out.println("\n\n");
        }
        System.out.println("Report with file name : Pending_Request[" + requestDate + "] generated!");
        reportMenu();
    }

    
}
