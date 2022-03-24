/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Main {

    private static Scanner scan = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        initializeCollection();

        boolean loginSuccess = true;
        do {
            System.out.println("======================================================");
            System.out.println("             Kidness Blood Management System            ");
            System.out.println("======================================================");
            System.out.print("Enter username: ");
            String name = scan.nextLine();
            System.out.print("Enter password: ");
            String pass = scan.nextLine();
            if (name.equals("Admin") && pass.equals("abc123")) {
                loginSuccess = true;
                System.out.println("\n" + ANSI_GREEN + "Welcome Admin !\n\n" + ANSI_RESET);
                mainMenu();
            } else {
                System.out.println(ANSI_RED + "Invalid username or password !\n\n" + ANSI_RESET);
                loginSuccess = false;
            }
        } while (!loginSuccess);
    }

    private static void initializeCollection() {
        DoneeManagement dm = new DoneeManagement();
        DonorManagement drm = new DonorManagement();
        RequestManagement rm = new RequestManagement();
        DonationManagement ddm = new DonationManagement();
        BloodBankInventory bbi = new BloodBankInventory();
        CampaignManagement cm = new CampaignManagement();

        dm.checkQueue();
        drm.checkQueue();
        rm.checkQueue();
        bbi.checkList();
    }

    public static void mainMenu() {
        DoneeManagement dm = new DoneeManagement();
        DonorManagement drm = new DonorManagement();
        RequestManagement rm = new RequestManagement();
        DonationManagement ddm = new DonationManagement();
        BloodBankInventory bbi = new BloodBankInventory();
        CampaignManagement cm = new CampaignManagement();

        System.out.println("======================================================");
        System.out.println("             Blood Bank Management System             ");
        System.out.println("======================================================");
        System.out.println("1. Donee Management");
        System.out.println("2. Donor Management");
        System.out.println("3. Blood Request Management");
        System.out.println("4. Blood Bank Inventory");
        System.out.println("5. Blood Donation Management");
        System.out.println("6. Campaign Management");
        System.out.println("7. Exit");
        int option = 0;

        try {
            System.out.print("\n" + "Enter your option: ");
            option = scan.nextInt();
            System.out.println("\n\n");
        } catch (Exception e) {
            scan.nextLine();
        }

        switch (option) {
            case 1:
                dm.doneeMenu();
                break;
            case 2:
                drm.donorMenu();
                break;
            case 3:
                rm.requestMainMenu();
                break;
            case 4:
                BloodBankInventory.bloodBankMenu();
                break;
            case 5:
                ddm.DonationMenu();
                break;
            case 6:
                cm.menu();
                break;
            case 7:
                System.exit(0);
            default:
                System.out.println(ANSI_RED + "Invalid option !\n\n" + ANSI_RESET);
                mainMenu();
                break;
        }
    }
}
