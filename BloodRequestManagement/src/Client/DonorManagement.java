/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import ADT.Queue;
import Entity.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ADT.QueueInterface;

/**
 *
 * @author
 */
public class DonorManagement {

    private final static QueueInterface<Donor> donorQueue = new Queue<>();
    private final QueueInterface<Donor> restoreQueue = new Queue<>();
    private final Scanner input = new Scanner(System.in);
    private final Scanner input1 = new Scanner(System.in);

    private static int entry;
    private boolean correction;

    public void checkQueue() {
        Donor r1 = new Donor("DR001", "hellor", "Kuala Lumpur", "018-1887302", "hellor@gmail.com");
        Donor r2 = new Donor("DR002", "yahor", "Kuala Lumpur", "014-1887302", "bellor@gmail.com");
        Donor r3 = new Donor("DR003", "bahor", "Kuala Lumpur", "015-1887302", "aellor@gmail.com");
        Donor r4 = new Donor("DR004", "cahor", "Kuala Lumpur", "016-1887302", "pellor@gmail.com");
        Donor r5 = new Donor("DR005", "pahor", "Kuala Lumpur", "017-1887302", "lellor@gmail.com");

        if (donorQueue.isEmpty()) {
            donorQueue.enqueue(r1);
            donorQueue.enqueue(r2);
            donorQueue.enqueue(r3);
            donorQueue.enqueue(r4);
            donorQueue.enqueue(r5);
        }

        entry = donorQueue.getTotalEntry();// take the last record in queue

    }

    public void donorMenu() {
        int select = 0;
        System.out.println("=====================");
        System.out.println("welcome to donor Menu");
        System.out.println("=====================");
        System.out.println("1.Display Donor detail");
        System.out.println("2.Add Donor detail");
        System.out.println("3.Update Donor detail");
        System.out.println("4.Search Donor detail");
        System.out.println("5.Remove Donor detail");
        System.out.println("6.Restore Donor detail");
        System.out.println("7.Back to main menu");
        try {
            System.out.print("\nChoose a function (1 - 6): ");
            select = input.nextInt();

        } catch (Exception e) {
            System.out.println("invalid input type , please insert one number which you want to use");
            System.out.println("click any key to continue ");
            input1.nextLine();
            donorMenu();
        }
        input.nextLine();
        if (select == 1) {
            displayDonor();
        } else if (select == 2) {
            addDonor();
        } else if (select == 3) {
            updateDonor();
        } else if (select == 4) {
            searchDonor();
        } else if (select == 5) {
            removeDonor();
        } else if (select == 6) {
            restoreDonor();
        } else if (select == 7) {
            Main.mainMenu();
        } else if (select >= 8 || select <= 0) {
            System.out.println("invalid choose , please insert again !!!! \n\n\n\n");
            donorMenu();
        }

    }

    public void donorHeader() {
        System.out.println("***********************************************************************************************************************************");
        System.out.println("|No. |Donor ID            |Donor Name               |Donor State             |Phone Number        |Email                           |");
        System.out.println("***********************************************************************************************************************************");
    }

    private void displayDonor() {
        displayDonorData();
        System.out.println("Press 'any key' to back to menu.");
        input.nextLine();
        donorMenu();
    }

    private void displayDonorData() {
        donorHeader();
        for (int i = 1; i <= donorQueue.getNumEntry(); i++) {

            System.out.println(" " + i + ".   " + donorQueue.getEntry(i));
        }
    }

    private void displayRestoreDonorData() {
        donorHeader();
        for (int i = 1; i <= restoreQueue.getNumEntry(); i++) {
            System.out.println(" " + i + ".   " + restoreQueue.getEntry(i));
        }
    }

    private void addDonor() {
        String addID = "DR" + String.format("%03d", entry += 1);
        //add donorname
        String addName = insertDonorName();
        //add donor state
        String addState = insertDonorState();
        //add donor phone number
        String addPhoneNumber = insertDonorPhoneNumber();
        //add donor email
        String addEmail = insertDonorEmail();
        Donor addDonor = new Donor(addID, addName, addState, addPhoneNumber, addEmail);
        donorQueue.enqueue(addDonor);
        System.out.println("\n\n\n");
        donorMenu();
    }

    private void updateDonor() {
        displayDonorData(); // display donor data
        try {
            int totalEntry = donorQueue.getTotalEntry(); //get all total quantity of data 
            System.out.print("\n\n Which donor would you want to modify?");
            int selectedEntry = input1.nextInt();
            System.out.println("\n\n");
            if (selectedEntry > donorQueue.getTotalEntry() || selectedEntry < 1) {
                System.out.println("Out of range of modify , please try again !!\n\n");
                updateDonor();
            }

            donorHeader();
            for (int i = 1; i <= donorQueue.getNumEntry(); i++) {
                if (selectedEntry == i) {
                    System.out.println(" " + i + ".   " + donorQueue.getEntry(i));
                    Donor modifyDonor = donorQueue.getEntry(i);
                    String modifyId = modifyDonor.getDonorId();
                    String modifyName = modifyDonor.getDonorName();
                    String modifyState = modifyDonor.getDonorState();
                    String modifyPhoneNumber = modifyDonor.getDonorPhoneNum();
                    String modifyEmail = modifyDonor.getDonorMail();
                    System.out.println("\nWhich categories would you want to modify ?\n");
                    System.out.println("1. Donor Name ");
                    System.out.println("2. Donor state ");
                    System.out.println("3. Donor Phone Number ");
                    System.out.println("4. Donor Email ");
                    System.out.print("Your Answer : ");
                    int selectModify = input1.nextInt();
                    input1.nextLine();

                    //add donorname
                    switch (selectModify) {
                        case 1:
                            modifyName = insertDonorName();
                            break;
                        case 2:
                            modifyState = insertDonorState();
                            break;
                        case 3:
                            modifyPhoneNumber = insertDonorPhoneNumber();
                            break;
                        case 4:
                            modifyEmail = insertDonorEmail();
                    }
                    donorQueue.replace(i - 1, new Donor(modifyId, modifyName, modifyState, modifyPhoneNumber, modifyEmail));
                }
            }
            System.out.println("\n\n\n");
            donorMenu();

        } catch (Exception e) {

            System.out.println("invalid input type , please insert one number which you want to modify");
            System.out.println("click any key to continue ");
            input1.nextLine();
            input1.nextLine();
            updateDonor();
        }
    }

    private void searchDonor() {
        Donor searchDonor = new Donor(); // use for search the data

        int search = 0;

        System.out.println("\n\nWhich categories would you want to search? ");
        System.out.println("1. Donor ID ");
        System.out.println("2. Donor Name ");
        System.out.println("3. Donor state ");
        System.out.println("4. Donor Phone Number ");
        System.out.println("5. Donor Email ");
        System.out.println("0. Exit ");
        System.out.print("Your Answer : ");
        try {

            search = input.nextInt(); // 

        } catch (Exception e) {
            System.out.println("invalid input type , please insert one number which you want to search");
            System.out.println("click any key to continue ");
            input1.nextLine();
            donorMenu();
        }

        if (search > 5 || search <= 0) {
            System.out.println("Out of Range , please try again !!!");
            searchDonor();
        } else {

            //if(searchId == searchDonor.getDonorName())
            //    
            switch (search) {
                case 0:
                    donorMenu();
                case 1:
                    String searchId = insertDonorId();
                    donorHeader();
                    for (int i = 1; i <= donorQueue.getNumEntry(); i++) {
                        searchDonor = donorQueue.getEntry(i);
                        if (searchId.equals(searchDonor.getDonorId())) {
                            System.out.println(" " + i + ".   " + donorQueue.getEntry(i));
                        }
                    }

                    break;

                case 2:
                    String searchName = insertDonorName();
                    donorHeader();
                    for (int i = 1; i <= donorQueue.getNumEntry(); i++) {
                        searchDonor = donorQueue.getEntry(i);
                        if (searchName.equals(searchDonor.getDonorName())) {
                            System.out.println(" " + i + ".   " + donorQueue.getEntry(i));
                        }
                    }
                    break;
                case 3:
                    String searchState = insertDonorState();
                    donorHeader();
                    for (int i = 1; i <= donorQueue.getNumEntry(); i++) {
                        searchDonor = donorQueue.getEntry(i);
                        if (searchState.equals(searchDonor.getDonorState())) {
                            System.out.println(" " + i + ".   " + donorQueue.getEntry(i));
                        }
                    }
                    break;
                case 4:
                    String searchPhoneNumber = insertDonorPhoneNumber();
                    donorHeader();
                    for (int i = 1; i <= donorQueue.getNumEntry(); i++) {
                        searchDonor = donorQueue.getEntry(i);
                        if (searchPhoneNumber.equals(searchDonor.getDonorPhoneNum())) {
                            System.out.println(" " + i + ".   " + donorQueue.getEntry(i));
                        }
                    }
                    break;
                case 5:
                    String searchEmail = insertDonorEmail();
                    donorHeader();
                    for (int i = 1; i <= donorQueue.getNumEntry(); i++) {
                        searchDonor = donorQueue.getEntry(i);
                        if (searchEmail.equals(searchDonor.getDonorMail())) {
                            System.out.println(" " + i + ".   " + donorQueue.getEntry(i));
                        }
                    }

                    break;
            }
            System.out.println("\n\n");
            donorMenu();
        }

    }

    private void removeDonor() {

        if (donorQueue.isEmpty()) {
            System.out.println("No data could be restore !!! \n\n\n");
            donorMenu();
        } else {
            int totalEntry = donorQueue.getTotalEntry(); //get all total quantity of data 
            displayDonorData(); // display donor data
            int remove = 0;
            try {
                System.out.print("\n\n Which donor would you want to detele? Answer : ");
                remove = input.nextInt(); // 
                if (remove > totalEntry) {
                    System.out.println("please insert valid number , thanks you \n\n");
                    removeDonor();
                } else {
                    Donor removeQueue = donorQueue.getEntry(remove);
                    Donor restore = new Donor(removeQueue.getDonorId(), removeQueue.getDonorName(), removeQueue.getDonorState(), removeQueue.getDonorPhoneNum(), removeQueue.getDonorMail());
                    restoreQueue.enqueue(restore);
                    donorQueue.remove(remove);

                    System.out.print("\n\n");
                }
            } catch (Exception e) {
                input.nextLine();
            }
            donorMenu();
        }
    }

    private void restoreDonor() {
        if (restoreQueue.isEmpty()) {
            System.out.println("No data could be restore !!! \n\n\n");
            donorMenu();
        } else {
            displayRestoreDonorData();
            System.out.println("Which data would you want to restore it?");
            System.out.print("Selection : ");
            int restoreKey = input.nextInt();
            Donor restoreData = restoreQueue.getEntry(restoreKey);
            String restoreID = "R" + String.format("%04d", entry += 1);
            Donor restore = new Donor(restoreID, restoreData.getDonorName(), restoreData.getDonorState(), restoreData.getDonorPhoneNum(), restoreData.getDonorMail());
            donorQueue.enqueue(restore);
            restoreQueue.remove(restoreKey);
            System.out.println("\n\n\n");
            donorMenu();
        }
    }

    //validate number
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //for all donorModule to validate and insert data
    private String insertDonorId() {
        String insertId;
        do {
            correction = true;
            System.out.print("Insert donor ID :");
            insertId = input1.nextLine();
            if (insertId.length() > 20) {
                System.out.println("Donor name cannot more than 20 words , please try again!!!");
                correction = false;
            } else if (insertId.isEmpty()) {
                System.out.println("Please Insert Donor name, Thanks you !!!");
                correction = false;
            }
        } while (correction == false);
        return insertId;
    }

    private String insertDonorName() {
        String insertName;
        do {
            correction = true;
            System.out.print("Insert donor Name :");
            insertName = input1.nextLine();
            if (insertName.length() > 20) {
                System.out.println("Donor name cannot more than 20 words , please try again!!!");
                correction = false;
            } else if (insertName.isEmpty()) {
                System.out.println("Please Insert Donor name, Thanks you !!!");
                correction = false;
            }
        } while (correction == false);
        return insertName;
    }

    private String insertDonorState() {
        String insertState;
        do {
            correction = true;
            System.out.print("Insert donor state :");
            insertState = input1.nextLine();
            if (insertState.length() > 15) {
                System.out.println("Invalid State , please try again !!!");
                correction = false;
            } else if (insertState.isEmpty()) {
                System.out.println("Please Insert Donor State, Thanks you !!!");
                correction = false;
            }
        } while (correction == false);
        return insertState;
    }

    private String insertDonorPhoneNumber() {
        String insertPhoneNumber;
        do {
            correction = true;
            System.out.print("Insert donor Phone Number :");
            insertPhoneNumber = input1.nextLine();
            if (insertPhoneNumber.charAt(0) != '0' || insertPhoneNumber.charAt(1) != '1') {
                System.out.println("Phone Number must start with \"01\" , please try again!!!");
                correction = false;

            } else if (insertPhoneNumber.length() != 10 && insertPhoneNumber.length() != 11) {
                System.out.println("Phone Number nord  10 number, please try again !!!");
                correction = false;

            } else if (isNumeric(insertPhoneNumber) == false) {
                System.out.println("Only Insert number no nord any symbol or word , please try again!!!");
                correction = false;

            } else if (insertPhoneNumber.isEmpty()) {

                System.out.println("Please insert Phone Number ,thanks you");
                correction = false;
            }

        } while (correction == false);
        insertPhoneNumber = insertPhoneNumber.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        return insertPhoneNumber;

    }

    private String insertDonorEmail() {
        String insertEmail;
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        do {
            correction = true;
            System.out.print("Insert Donor email : ");
            insertEmail = input1.nextLine();
            Matcher mat = pattern.matcher(insertEmail);
            if (mat.matches() == false) {
                System.out.println("Invalid email , please try again !!!");
                correction = false;

            } else if (insertEmail.isEmpty()) {
                System.out.println("Please Insert Donor email, Thanks you !!!");
                correction = false;
            }
        } while (correction == false);
        return insertEmail;
    }

    public static boolean getValidDonor(String donorId) {
        for (int i = 1; i < donorQueue.getNumEntry() + 1; i++) {
            Donor donorChoose = (Donor) donorQueue.getEntry(i);
            String id = donorChoose.getDonorId();
            if (donorId.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
