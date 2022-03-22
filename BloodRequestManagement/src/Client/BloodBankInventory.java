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
 * @author Chai Jia Hao
 * @author Gan Wei Zhe
 */
public class BloodBankInventory {

    public static SortedArrayListInterface<BloodBank> bloodList = new SortedArrayList<BloodBank>();
    private static final Scanner scan = new Scanner(System.in);
    public static BloodBank bb = new BloodBank();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public void checkList() {
        if (bloodList.isEmpty()) {
            bloodList.add(new BloodBank("A", 100));
            bloodList.add(new BloodBank("B", 100));
            bloodList.add(new BloodBank("O", 100));
            bloodList.add(new BloodBank("AB", 100));

            BloodBank b1 = bloodList.getEntry(1);
            BloodBank b2 = bloodList.getEntry(3);
            BloodBank b3 = bloodList.getEntry(4);

            Request r1 = new Request("R0001", new Donee("D0001"), "A", 10, "03-08-2021", "Approved", 1);
            Request r2 = new Request("R0002", new Donee("D0002"), "A", 20, "04-08-2021", "Approved", 1);
            Request r3 = new Request("R0003", new Donee("D0002"), "B", 30, "05-08-2021", "Approved", 5);
            Request r4 = new Request("R0004", new Donee("D0002"), "O", 40, "06-08-2021", "Approved", 1);
            Request r5 = new Request("R0005", new Donee("D0003"), "B", 50, "07-08-2021", "Approved", 1);
//            b1.addRequest(r1);
//            b1.addRequest(r2);
//            b2.addRequest(r3);
//            b3.addRequest(r4);
//            b2.addRequest(r5);
        }
    }

    public static void bloodBankMenu() {
        int option = 0;
        System.out.println("\n\n======================================");
        System.out.println("||       Blood Bank Inventory       ||");
        System.out.println("======================================");
        bloodBankHeader();
        for (int i = 1; i <= bloodList.getNumberOfEntries(); i++) {
            System.out.println(i + ". " + bloodList.getEntry(i));
        }
        System.out.println("\n\n");
        System.out.println("1. Show recent request");
        System.out.println("2. Show recent donation");
        System.out.println("3. Back to main menu");

        try {
            System.out.print("\nEnter your option: ");
            option = scan.nextInt();
            System.out.println("\n\n");
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
        }

        if (option == 1) {
            displayRecentRequest();
        } else if (option == 2) {
            //displayRecentDonation();
        } else if (option == 3) {
            Main.mainMenu();
        } else {
            System.out.println(ANSI_RED + "Invalid option choose !" + ANSI_RESET);
            bloodBankMenu();
        }
    }

    public static void displayRecentRequest() {
        System.out.println("\n\n======================================");
        System.out.println("||       Blood Bank Inventory       ||");
        System.out.println("======================================");
        for (int i = 1; i <= bloodList.getNumberOfEntries(); i++) {
            System.out.println(i + ". " + bloodList.getEntry(i).toString2());
        }
        System.out.println(ANSI_BLUE + "Press 'Enter' to back to menu.\n" + ANSI_RESET);
        scan.nextLine();
        bloodBankMenu();
    }

    public static void bloodBankHeader() {
        System.out.println("No.\tBlood Type\tBlood Amount");
        System.out.println("======================================");
    }

    public static void addToBloodBank(Request r) {
        int entry = 0;
        if ("A".equals(r.getBloodType())) {
            entry = 1;
        } else if ("AB".equals(r.getBloodType())) {
            entry = 2;
        } else if ("B".equals(r.getBloodType())) {
            entry = 3;
        } else if ("O".equals(r.getBloodType())) {
            entry = 4;
        }
        BloodBank updNewRequest = bloodList.getEntry(entry);
        updNewRequest.addRequest(r);
        //bloodList.update(updNewRequest, entry);
        bb.addRequest(r);
    }

    public static void displayReviewRequest() {
        if (!bb.requestStack.isEmpty()) {
            int i = 1;
            int top = bb.requestStack.getNumOfEntry();
            for (int j = top; j >= 0; j--) {
                System.out.println(" " + i + ". " + bb.requestStack.peek(j));
                i++;
            }
        } else {
            System.out.println("No record found !");
        }
    }

    public static boolean updBloodRequest(String bloodType, int bloodAmount) {
        boolean success = true;
        int newAmount = 0;

        for (int i = 1; i <= bloodList.getNumberOfEntries(); i++) {
            BloodBank selectedBlood = bloodList.getEntry(i);
            if (selectedBlood.getBloodType().equals(bloodType)) {
                newAmount = selectedBlood.getBloodAmount() - bloodAmount;
                if (newAmount > 0) {
                    selectedBlood.setBloodAmount(newAmount);
                    success = true;
                } else {
                    success = false;
                }
            }
        }
        System.out.println(newAmount);
        return success;
    }

    public static void reviewedRequestReport() {

        String requestDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        if (bb.requestStack.isEmpty()) {
            System.out.println("The reviewed request is empty, no report generated!");
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/Reviewed_Request[" + requestDate + "].txt", false));
                writer.write("-----------------------------------------------------------------------------------------------------------\n");
                writer.write("\t\t\t\t\tReviewed Request Report\n");
                writer.write("-----------------------------------------------------------------------------------------------------------\n");
                writer.write("Request ID\tDonee ID\tBlood Type\tRequest Date\tAmount\t\tStatus\t\tNeed Level\n");
                writer.write("-----------------------------------------------------------------------------------------------------------\n");
                writer.close();
            } catch (Exception e) {
                System.out.println("\n\n");
            }
            for (int i = bb.requestStack.getNumOfEntry(); i >= 0; i--) {
                Request reportData = bb.requestStack.peek(i);
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("src/Reviewed_Request[" + requestDate + "].txt", true));
                    writer.append(reportData.getRequestId() + "\t\t" + reportData.getDoneeId().toString2() + "\t\t"
                            + reportData.getBloodType() + "\t\t" + reportData.getRequestDate() + "\t"
                            + reportData.getRequestAmount() + "\t\t" + reportData.getRequestStatus() + "\t\t" + reportData.getNeedLevel() + "\n");
                    writer.close();
                } catch (Exception e) {
                    System.out.println("\n\n");
                }
            }
        }

        int count = bb.requestStack.getNumOfEntry() + 1;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Reviewed_Request[" + requestDate + "].txt", true));
            writer.append("\n------------------------------------------END OF REPORT---------------------------------------------------\n");
            writer.append("\nDate of report generated :" + requestDate);
            writer.append("\nTotal reviewed request : " + count);

            writer.close();
        } catch (Exception e) {
            System.out.println("\n\n");
        }
        System.out.println("Report with file name : Reviewed_Request[" + requestDate + "] generated!");
    }
}
