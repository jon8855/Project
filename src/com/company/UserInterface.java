package com.company;

import java.util.List;
import java.util.Scanner;

public class UserInterface{

    public void userChoices(List bikes, List numbers, List names){
        Methods opt = new Methods();
        Scanner in = new Scanner(System.in);
        System.out.println("Please select your option from the following menu: \n" +
                "Read: Read an inventory delivery file\n" +
                "Enter: Enter a part\n" +
                "Sell: Sell a part \n" +
                "Display: Display a part\n" +
                "Sort Name: Sort parts by part name\n" +
                "Sort Number: Sort parts by part number\n" +
                "Add Van: Adds Sales Van to fleet\n" +
                "Transfer File: Reads text file of parts to be transferred amongst WareHouse and Fleet\n" +
                "Quit: \n" +
                "Enter your choice:");
        String choice = in.nextLine();

        if(choice.equalsIgnoreCase("Read")){
            opt.readFile();
            opt.goAgain(bikes, numbers, names);
        }
        else if(choice.equalsIgnoreCase("Enter")){
            opt.manuEnter(bikes);
            opt.goAgain(bikes, numbers, names);
        }

        else if(choice.equalsIgnoreCase("Sell")){
            opt.sellPart(bikes);
            opt.goAgain(bikes,numbers, names);
        }
        else if(choice.equalsIgnoreCase("Display")){
            opt.showPart(bikes);
            opt.goAgain(bikes,numbers, names);
        }
        else if(choice.equalsIgnoreCase("Sort Name")){
            System.out.println("What warehouse would you like to sort?");
            String warehouse = in.nextLine();
            if(!warehouse.equalsIgnoreCase("main warehouse")){
                opt.VaninitialInventory(warehouse);
            }
            opt.sortName(bikes, names, warehouse);
            opt.goAgain(bikes,numbers,names);
        }
        else if(choice.equalsIgnoreCase("Sort Number")){
            System.out.println("What warehouse would you like to sort?");
            String warehouse = in.nextLine();
            if(!warehouse.equalsIgnoreCase("main warehouse")){
                opt.VaninitialInventorysortnum(warehouse);
            }
            opt.sortNumber(bikes, numbers, warehouse);
            opt.goAgain(bikes,numbers,names);
        }
        else if(choice.equalsIgnoreCase("Quit")){
            opt.userQuit(bikes);
        }
        else if(choice.equalsIgnoreCase("Add Van")){
            opt.addVan();
            opt.goAgain(bikes,numbers,names);
        }
        else if(choice.equalsIgnoreCase("transfer file")){
            opt.readVanFile(bikes);
            opt.goAgain(bikes,numbers,names);
        }
        else{
            System.out.println("Error: Invalid Choice\nEnter a new command\n");
            userChoices(bikes, numbers, names);
        }
    }
}
