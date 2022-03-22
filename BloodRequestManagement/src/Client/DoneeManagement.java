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
 * @author Lim Jun Hua
 */
public class DoneeManagement {

    private final static QueueInterface<Donee> doneeQueue = new Queue<>();
    private final QueueInterface<Donee> restoreQueue = new Queue<>();
    private final Scanner input = new Scanner(System.in);
    private final Scanner input1 = new Scanner(System.in);

    private static int entry;
    private boolean correction;

    public void checkQueue() {
        Donee r1 = new Donee("D0001", "hello", "Kuala Lumpur", "018-2037881", "hello@gmail.com");
        Donee r2 = new Donee("D0002", "yaho", "Kuala Lumpur", "014-2037881", "bello@gmail.com");
        Donee r3 = new Donee("D0003", "baho", "Kuala Lumpur", "015-2037881", "aello@gmail.com");
        Donee r4 = new Donee("D0004", "caho", "Kuala Lumpur", "016-2037881", "pello@gmail.com");
        Donee r5 = new Donee("D0005", "paho", "Kuala Lumpur", "017-2037881", "lello@gmail.com");

        if (doneeQueue.isEmpty()) {
            doneeQueue.enqueue(r1);
            doneeQueue.enqueue(r2);
            doneeQueue.enqueue(r3);
            doneeQueue.enqueue(r4);
            doneeQueue.enqueue(r5);
        }

        entry = doneeQueue.getTotalEntry();// take the last record in queue

    }

    public void doneeMenu() {
        int select = 0;
        System.out.println("=====================");
        System.out.println("welcome to donee Menu");
        System.out.println("=====================");
        System.out.println("1.Display Donee detail");
        System.out.println("2.Add Donee detail");
        System.out.println("3.Update Donee detail");
        System.out.println("4.Search Donee detail");
        System.out.println("5.Remove Donee detail");
        System.out.println("6.Restore Donee detail");
        System.out.println("7.Back to main menu");
        try {
            System.out.print("\nChoose a function (1 - 6): ");
            select = input.nextInt();

        } catch (Exception e) {
            System.out.println("invalid input type , please insert one number which you want to use");
            System.out.println("click any key to continue ");
            input1.nextLine();
            doneeMenu();
        }
        input.nextLine();
        if (select == 1) {
            displayDonee();
        } else if (select == 2) {
            addDonee();
        } else if (select == 3) {
            updateDonee();
        } else if (select == 4) {
            searchDonee();
        } else if (select == 5) {
            removeDonee();
        } else if (select == 6) {
            restoreDonee();
        } else if (select == 7) {
            Main.mainMenu();
        } else if (select >= 8 || select <= 0) {
            System.out.println("invalid choose , please insert again !!!! \n\n\n\n");
            doneeMenu();
        }

    }

    public void doneeHeader() {
        System.out.println("***********************************************************************************************************************************");
        System.out.println("|No. |Donee ID            |Donee Name               |Donee State             |Phone Number        |Email                           |");
        System.out.println("***********************************************************************************************************************************");
    }

    private void displayDonee() {
        displayDoneeData();
        System.out.println("Press 'any key' to back to menu.");
        input.nextLine();
        doneeMenu();
    }

    private void displayDoneeData() {
        doneeHeader();
        for (int i = 1; i <= doneeQueue.getNumEntry(); i++) {

            System.out.println(" " + i + ".   " + doneeQueue.getEntry(i));
        }
    }

    private void displayRestoreDoneeData() {
        doneeHeader();
        for (int i = 1; i <= restoreQueue.getNumEntry(); i++) {
            System.out.println(" " + i + ".   " + restoreQueue.getEntry(i));
        }
    }

    private void addDonee() {
        String addID = "D" + String.format("%04d", entry += 1);
        //add doneename
        String addName = insertDoneeName();
        //add donee state
        String addState = insertDoneeState();
        //add donee phone number
        String addPhoneNumber = insertDoneePhoneNumber();
        //add donee email
        String addEmail = insertDoneeEmail();
        Donee addDonee = new Donee(addID, addName, addState, addPhoneNumber, addEmail);
        doneeQueue.enqueue(addDonee);
        System.out.println("\n\n\n");
        doneeMenu();
    }

    private void updateDonee() {
        displayDoneeData(); // display donee data
        try {
            int totalEntry = doneeQueue.getTotalEntry(); //get all total quantity of data 
            System.out.print("\n\n Which donee would you want to modify?");
            int selectedEntry = input1.nextInt();
            System.out.println("\n\n");
            if (selectedEntry > doneeQueue.getTotalEntry() || selectedEntry < 1) {
                System.out.println("Out of range of modify , please try again !!\n\n");
                updateDonee();
            }

            doneeHeader();
            for (int i = 1; i <= doneeQueue.getNumEntry(); i++) {
                if (selectedEntry == i) {
                    System.out.println(" " + i + ".   " + doneeQueue.getEntry(i));
                    Donee modifyDonee = doneeQueue.getEntry(i);
                    String modifyId = modifyDonee.getDoneeId();
                    String modifyName = modifyDonee.getDoneeName();
                    String modifyState = modifyDonee.getDoneeState();
                    String modifyPhoneNumber = modifyDonee.getDoneePhone();
                    String modifyEmail = modifyDonee.getDoneeEmail();
                    System.out.println("\nWhich categories would you want to modify ?\n");
                    System.out.println("1. Donee Name ");
                    System.out.println("2. Donee state ");
                    System.out.println("3. Donee Phone Number ");
                    System.out.println("4. Donee Email ");
                    System.out.print("Your Answer : ");
                    int selectModify = input1.nextInt();
                    input1.nextLine();

                    //add doneename
                    switch (selectModify) {
                        case 1:
                            modifyName = insertDoneeName();
                            break;
                        case 2:
                            modifyState = insertDoneeState();
                            break;
                        case 3:
                            modifyPhoneNumber = insertDoneePhoneNumber();
                            break;
                        case 4:
                            modifyEmail = insertDoneeEmail();
                    }
                    doneeQueue.replace(i - 1, new Donee(modifyId, modifyName, modifyState, modifyPhoneNumber, modifyEmail));
                }
            }
            System.out.println("\n\n\n");
            doneeMenu();

        } catch (Exception e) {

            System.out.println("invalid input type , please insert one number which you want to modify");
            System.out.println("click any key to continue ");
            input1.nextLine();
            input1.nextLine();
            updateDonee();
        }
    }

    private void searchDonee() {
        Donee searchDonee = new Donee(); // use for search the data

        int search = 0;

        System.out.println("\n\nWhich categories would you want to search? ");
        System.out.println("1. Donee ID ");
        System.out.println("2. Donee Name ");
        System.out.println("3. Donee state ");
        System.out.println("4. Donee Phone Number ");
        System.out.println("5. Donee Email ");
        System.out.println("0. Exit ");
        System.out.print("Your Answer : ");
        try {

            search = input.nextInt(); // 

        } catch (Exception e) {
            System.out.println("invalid input type , please insert one number which you want to search");
            System.out.println("click any key to continue ");
            input1.nextLine();
            doneeMenu();
        }

        if (search > 5 || search <= 0) {
            System.out.println("Out of Range , please try again !!!");
            searchDonee();
        } else {

            //if(searchId == searchDonee.getDoneeName())
            //    
            switch (search) {
                case 0:
                    doneeMenu();
                case 1:
                    String searchId = insertDoneeId();
                    doneeHeader();
                    for (int i = 1; i <= doneeQueue.getNumEntry(); i++) {
                        searchDonee = doneeQueue.getEntry(i);
                        if (searchId.equals(searchDonee.getDoneeId())) {
                            System.out.println(" " + i + ".   " + doneeQueue.getEntry(i));
                        }
                    }

                    break;

                case 2:
                    String searchName = insertDoneeName();
                    doneeHeader();
                    for (int i = 1; i <= doneeQueue.getNumEntry(); i++) {
                        searchDonee = doneeQueue.getEntry(i);
                        if (searchName.equals(searchDonee.getDoneeName())) {
                            System.out.println(" " + i + ".   " + doneeQueue.getEntry(i));
                        }
                    }
                    break;
                case 3:
                    String searchState = insertDoneeState();
                    doneeHeader();
                    for (int i = 1; i <= doneeQueue.getNumEntry(); i++) {
                        searchDonee = doneeQueue.getEntry(i);
                        if (searchState.equals(searchDonee.getDoneeState())) {
                            System.out.println(" " + i + ".   " + doneeQueue.getEntry(i));
                        }
                    }
                    break;
                case 4:
                    String searchPhoneNumber = insertDoneePhoneNumber();
                    doneeHeader();
                    for (int i = 1; i <= doneeQueue.getNumEntry(); i++) {
                        searchDonee = doneeQueue.getEntry(i);
                        if (searchPhoneNumber.equals(searchDonee.getDoneePhone())) {
                            System.out.println(" " + i + ".   " + doneeQueue.getEntry(i));
                        }
                    }
                    break;
                case 5:
                    String searchEmail = insertDoneeEmail();
                    doneeHeader();
                    for (int i = 1; i <= doneeQueue.getNumEntry(); i++) {
                        searchDonee = doneeQueue.getEntry(i);
                        if (searchEmail.equals(searchDonee.getDoneeEmail())) {
                            System.out.println(" " + i + ".   " + doneeQueue.getEntry(i));
                        }
                    }

                    break;
            }
            System.out.println("\n\n");
            doneeMenu();
        }

    }

    private void removeDonee() {

        if (doneeQueue.isEmpty()) {
            System.out.println("No data could be restore !!! \n\n\n");
            doneeMenu();
        } else {
            int totalEntry = doneeQueue.getTotalEntry(); //get all total quantity of data 
            displayDoneeData(); // display donee data
            int remove = 0;
            try {
                System.out.print("\n\n Which donee would you want to detele? Answer : ");
                remove = input.nextInt(); // 
                if (remove > totalEntry) {
                    System.out.println("please insert valid number , thanks you \n\n");
                    removeDonee();
                } else {
                    Donee removeQueue = doneeQueue.getEntry(remove);
                    Donee restore = new Donee(removeQueue.getDoneeId(), removeQueue.getDoneeName(), removeQueue.getDoneeState(), removeQueue.getDoneePhone(), removeQueue.getDoneeEmail());
                    restoreQueue.enqueue(restore);
                    doneeQueue.remove(remove);

                    System.out.print("\n\n");
                }
            } catch (Exception e) {
                input.nextLine();
            }
            doneeMenu();
        }
    }

    private void restoreDonee() {
        if (restoreQueue.isEmpty()) {
            System.out.println("No data could be restore !!! \n\n\n");
            doneeMenu();
        } else {
            displayRestoreDoneeData();
            System.out.println("Which data would you want to restore it?");
            System.out.print("Selection : ");
            int restoreKey = input.nextInt();
            Donee restoreData = restoreQueue.getEntry(restoreKey);
            String restoreID = "R" + String.format("%04d", entry += 1);
            Donee restore = new Donee(restoreID, restoreData.getDoneeName(), restoreData.getDoneeState(), restoreData.getDoneePhone(), restoreData.getDoneeEmail());
            doneeQueue.enqueue(restore);
            restoreQueue.remove(restoreKey);
            System.out.println("\n\n\n");
            doneeMenu();
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

    //for all doneeModule to validate and insert data
    private String insertDoneeId() {
        String insertId;
        do {
            correction = true;
            System.out.print("Insert donee ID :");
            insertId = input1.nextLine();
            if (insertId.length() > 20) {
                System.out.println("Donee name cannot more than 20 words , please try again!!!");
                correction = false;
            } else if (insertId.isEmpty()) {
                System.out.println("Please Insert Donee name, Thanks you !!!");
                correction = false;
            }
        } while (correction == false);
        return insertId;
    }

    private String insertDoneeName() {
        String insertName;
        do {
            correction = true;
            System.out.print("Insert donee Name :");
            insertName = input1.nextLine();
            if (insertName.length() > 20) {
                System.out.println("Donee name cannot more than 20 words , please try again!!!");
                correction = false;
            } else if (insertName.isEmpty()) {
                System.out.println("Please Insert Donee name, Thanks you !!!");
                correction = false;
            }
        } while (correction == false);
        return insertName;
    }

    private String insertDoneeState() {
        String insertState;
        do {
            correction = true;
            System.out.print("Insert donee state :");
            insertState = input1.nextLine();
            if (insertState.length() > 15) {
                System.out.println("Invalid State , please try again !!!");
                correction = false;
            } else if (insertState.isEmpty()) {
                System.out.println("Please Insert Donee State, Thanks you !!!");
                correction = false;
            }
        } while (correction == false);
        return insertState;
    }

    private String insertDoneePhoneNumber() {
        String insertPhoneNumber;
        do {
            correction = true;
            System.out.print("Insert donee Phone Number :");
            insertPhoneNumber = input1.nextLine();
            if (insertPhoneNumber.charAt(0) != '0' || insertPhoneNumber.charAt(1) != '1') {
                System.out.println("Phone Number must start with \"01\" , please try again!!!");
                correction = false;

            } else if (insertPhoneNumber.length() != 10 && insertPhoneNumber.length() != 11) {
                System.out.println("Phone Number need  10 number, please try again !!!");
                correction = false;

            } else if (isNumeric(insertPhoneNumber) == false) {
                System.out.println("Only Insert number no need any symbol or word , please try again!!!");
                correction = false;

            } else if (insertPhoneNumber.isEmpty()) {

                System.out.println("Please insert Phone Number ,thanks you");
                correction = false;
            }

        } while (correction == false);
        insertPhoneNumber = insertPhoneNumber.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        return insertPhoneNumber;

    }

    private String insertDoneeEmail() {
        String insertEmail;
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        do {
            correction = true;
            System.out.print("Insert Donee email : ");
            insertEmail = input1.nextLine();
            Matcher mat = pattern.matcher(insertEmail);
            if (mat.matches() == false) {
                System.out.println("Invalid email , please try again !!!");
                correction = false;

            } else if (insertEmail.isEmpty()) {
                System.out.println("Please Insert Donee email, Thanks you !!!");
                correction = false;
            }
        } while (correction == false);
        return insertEmail;
    }

    public static boolean getValidDonee(String doneeId) {
        for (int i = 1; i < doneeQueue.getNumEntry() + 1; i++) {
            Donee doneeChoose = (Donee) doneeQueue.getEntry(i);
            String id = doneeChoose.getDoneeId();
            if (doneeId.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
