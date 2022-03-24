/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import ADT.*;
import Entity.*;
import ADT.ListInterface;
import Entity.Campaign;
import Entity.NeedList;
import java.util.Scanner;


/**
 *
 * @author teren
 */
public class CampaignManagement {

    public static int menu() {
        int option = 0;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n=============================");
            System.out.println("     Campaign Management     ");
            System.out.println("=============================");
            System.out.println("Welcome, please choose the option below.");
            System.out.println("----------------------------------------");
            System.out.println("1 - View Campaign");
            System.out.println("2 - View Need List");
            System.out.println("3 - Report");
            System.out.println("4 - Exit\n");
            System.out.print("Choose an option: ");
            option = input.nextInt();
        } while (option > 4 || option < 1);

        
         switch (option) {
            case 1:
                campaignMenu();
                break;
            case 2:
                needListMenu();
                break;
            case 4:
                Main.mainMenu();
                break;
            default:
                System.out.println("Invalid option selection! Please enter again (1 - 7)");
                break;
         }
         
         return option;
    }

    public static int campaignMenu() {
        int option;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n=============================");
            System.out.println("     Campaign Management     ");
            System.out.println("=============================");
            System.out.println("Welcome, please choose the option below.");
            System.out.println("----------------------------------------");
            System.out.println("1 - Create a new Campaign");
            System.out.println("2 - Edit Campaign");
            System.out.println("3 - Delete Campaign");
            System.out.println("4 - Back to main menu\n");
            System.out.print("Choose an option: ");
            option = input.nextInt();
        } while (option > 4 || option < 1);

        return option;
    }

    public static int needListMenu() {
        int option;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n=============================");
            System.out.println("     Campaign Management     ");
            System.out.println("=============================");
            System.out.println("Welcome, please choose the option below.");
            System.out.println("----------------------------------------");
            System.out.println("1 - Add Need List");
            System.out.println("2 - Edit Need List");
            System.out.println("3 - Delete Need List");
            System.out.println("4 - Back to main menu\n");
            System.out.print("Choose an option: ");
            option = input.nextInt();
        } while (option > 4 || option < 1);
        return option;
    }

    public void mainCampaignMenu() {
        ListInterface<Campaign> campaignList = new List<Campaign>();
        ListInterface<NeedList> needlist = new List<NeedList>();
        
        //Campaign default data that is addded to the list
        Campaign c1 = new Campaign("1001", "Blood Donation 2022", "19/8/2022");

        
        //NeedList default data that is added to the list
        NeedList n1 = new NeedList("Table", 30, 120.00);
        NeedList n2 = new NeedList("Chair", 20, 90.00);
        NeedList n3 = new NeedList("Cloth", 50, 300.00);
        NeedList n4 = new NeedList("Tissue", 100, 230.00);
        NeedList n5 = new NeedList("Shampoo", 30, 150.00);

        needlist.add(n1);
        needlist.add(n2);
        needlist.add(n3);       
        needlist.add(n4);
        needlist.add(n5);
        c1.setNeedlist(needlist);

        campaignList.add(c1);

        Scanner input = new Scanner(System.in);

        int choice;
        int campaignOption;
        int needListOption;
        do {
            choice = menu();  //This function is to display main menu of the Campaign Management
            switch (choice) {
                case 1 : {
                    do {
                        //Campaign Add Function
                        campaignOption = campaignMenu(); // This function is to display the Campaign Menu.
                        switch (campaignOption) {
                            case 1 : {
                                //Enter the id name and date to create a campaign
                                System.out.print("ID: ");
                                String id = input.nextLine();
                                
                                Campaign compareID;
                                for (int i = 1; i <= campaignList.arraySize(); i++){
                                    compareID = campaignList.getRecord(i);
                                    while (compareID.getId().equals(id)){
                                        System.out.println("There is the same ID, Please enter another ID");
                                        System.out.print("ID: ");
                                        id = input.nextLine();
                                    }
                                }
                                System.out.print("Name: ");
                                String name = input.nextLine();

                                System.out.print("Date: ");
                                String date = input.nextLine();

                                
                                
                                Campaign camp = new Campaign(id, name, date);

                                campaignList.add(camp);

                                //Add Need List
                                System.out.print("Do you want to add need list?(Y/N): ");
                                String userInput = input.nextLine();

                                if (userInput.equals("y") || userInput.equals("Y")) {
                                    String inputCont;
                                    ListInterface<NeedList> newNeedlist = new List<NeedList>();
                                    do {
                                        System.out.print("Item Name: ");
                                        String itemPrice = input.nextLine();

                                        System.out.print("Quantity: ");
                                        int itemQuantity = input.nextInt();

                                        System.out.print("Cash: ");
                                        double price = input.nextDouble();

                                        NeedList need = new NeedList(itemPrice, itemQuantity, price);
                                        newNeedlist.add(need);
                                        camp.setNeedlist(newNeedlist);

                                        System.out.print("Do you want to continue add?(Y/N): ");
                                        input.nextLine();
                                        inputCont = input.nextLine();
                                    } while (inputCont.toUpperCase().equals("Y"));

                                }

                            }
                            case 2 : {
                                //Update Campaign
                                campaignList.sort((List)campaignList);
                                System.out.println("Campaign list\n" + campaignList.toString());
                                System.out.print("Which one you want to edit?: ");
                                String arrayRow = input.nextLine();
                                for (int i = 1; i <= campaignList.arraySize(); i++) {
                                    Campaign compareID = campaignList.getRecord(i);
                                    int arrayRowNumber = i;
                                    if (compareID.getId().equals(arrayRow)) {

                                        String id = compareID.getId();
                                        ListInterface<NeedList> list = compareID.getNeedlist();

                                        System.out.print("Name: ");
                                        String name = input.nextLine();

                                        System.out.print("Date: ");
                                        String date = input.nextLine();

                                        Campaign updateCamp = new Campaign(id, name, date);                                        
                                        updateCamp.setNeedlist(list);
                                        campaignList.replace(arrayRowNumber, updateCamp);

                                    }

                                }
                            }
                            case 3 : {
                                //Delete Campaign
                                campaignList.sort((List)campaignList);
                                System.out.println("Campaign list\n" + campaignList.toString());
                                System.out.print("Which one you want to delete?: ");
                                String row = input.nextLine();
                                for (int i = 1; i <= campaignList.arraySize(); i++) {
                                    Campaign compareID = campaignList.getRecord(i);
                                    int arrayRowNumber = i;
                                    if (compareID.getId().equals(row)) {

                                        campaignList.remove(arrayRowNumber);
                                    }
                                }
                            }
                            case 4 : {
                                break;
                            }
                        }
                    } while (campaignOption != 4);
                }
                case 2 : {
                    do {
                        //Need List Function
                        needListOption = needListMenu(); // This function is to display the Need List Menu.
                        switch (needListOption) {
                            case 1 : {
                                //Add Need List from a specific campaign
                                System.out.println(campaignList.toString());
                                //Choose campaign id. For example, 1001.
                                System.out.print("Which campaign you want to add into need list?: ");
                                String inputRow = input.nextLine();
                                for (int i = 1; i <= campaignList.arraySize(); i++) {
                                    Campaign compareID = campaignList.getRecord(i);
                                    if (compareID.getId().equals(inputRow)) {
                                        System.out.println("\nEnter the price, item and the quantity that you want to donate\n");
                                        ListInterface<NeedList> list = compareID.getNeedlist();
                                        System.out.print("Item Name: ");
                                        String itemPrice = input.nextLine();

                                        System.out.print("Quantity: ");
                                        int itemQuantity = input.nextInt();

                                        System.out.print("Cash: RM");
                                        double price = input.nextDouble();

                                        NeedList need = new NeedList(itemPrice, itemQuantity, price);

                                        list.add(need);
                                        compareID.setNeedlist(list);
                                    }
                                }
                            }
                            case 2 : {
                                //Edit Need List from a specific campaign
                                campaignList.sort((List)campaignList);
                                System.out.println(campaignList.toString());
                                //Choose campaign id. For example, 1001.
                                System.out.print("Select the campaign you want to edit the need list: ");
                                String inputRow = input.nextLine();
                                ListInterface<NeedList> list;
                                for (int i = 1; i <= campaignList.arraySize(); i++) {
                                    Campaign compareID = campaignList.getRecord(i);
                                    list = compareID.getNeedlist();
                                    if (compareID.getId().equals(inputRow)) {
                                        //choose 1, 2 or 3 in order to edit the need list.
                                        System.out.print("Which need list you want to edit? : ");
                                        int selection = input.nextInt();
                                        input.nextLine();
                                        System.out.print("Item Name: ");
                                        String itemPrice = input.nextLine();

                                        System.out.print("Quantity: ");
                                        int itemQuantity = input.nextInt();

                                        System.out.print("Cash: RM");
                                        double price = input.nextDouble();

                                        NeedList need = new NeedList(itemPrice, itemQuantity, price);

                                        list.replace(selection, need);
                                    }
                                }
                            }
                            case 3 : {
                                //Delete Need List from a specific campaign
                                campaignList.sort((List)campaignList);
                                System.out.println(campaignList.toString());
                                //Choose campaign id. For example, 1001.
                                System.out.print("Select the campaign you want to delete the need list: ");
                                String inputRow = input.nextLine();
                                ListInterface<NeedList> list;
                                for (int i = 1; i <= campaignList.arraySize(); i++) {
                                    Campaign compareID = campaignList.getRecord(i);
                                    list = compareID.getNeedlist();
                                    if (compareID.getId().equals(inputRow)) {
                                        //choose 1, 2 or 3 in order to edit the need list.
                                        System.out.print("Which need list you want to edit? : ");
                                        int selection = input.nextInt();

                                        list.remove(selection);

                                    }
                                }
                            }
                            case 4 : {
                                break;
                            }

                        }
                    } while (needListOption != 4);
                }
                case 3 : {
                    //Report (to display all campaign with the need list)
                    Scanner pauser = new Scanner(System.in);
                    System.out.printf("\n=======================================\n");
                    System.out.printf("%6s %10s %15s\n", "ID", "Name", "Date");
                    System.out.printf("=======================================\n");
                    campaignList.sort((List)campaignList);
                    System.out.println(campaignList.toString());

                    System.out.println("Press Any Key To Continue...");
                    pauser.nextLine();

                }
                case 4 : {
                    //exit program
                    Main.mainMenu();
                    break;
                }
                default :
                    //User wrong input
                    System.out.println("This is not a valid Menu Option! Please Select Another");
            }
        } while (choice != 4);
    }
}
