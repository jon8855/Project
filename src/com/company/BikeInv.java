package com.company;

import java.util.Scanner;

public class BikeInv {
    public int partNum;
    public String partName;
    public double listPrice;
    public double salePrice;
    public boolean sale;
    public int quantity;
    Scanner in = new Scanner(System.in);

    public String getPartName(){
        this.partName = partName;
        return partName;
    }
    public int getPartNum(){
        this.partNum = partNum;
        return partNum;
    }
    public double getListPrice(){
        this.listPrice = listPrice;
        return listPrice;
    }
    public double getSalePrice(){
        this.salePrice = salePrice;
        return salePrice;
    }
    public boolean isSale(){
        this.sale = sale;
        return sale;
    }
    public int getQuantity(){
        this.quantity = quantity;
        return quantity;
    }
    public void readFile(){
        System.out.println("Please enter the name of your file:");
        String file = in.nextLine();
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine()){
            Scanner useDelimiter = sc.useDelimiter(",");
            String line = useDelimiter.nextLine();
            System.out.println(line);
        }
    }

}
