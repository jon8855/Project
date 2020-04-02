package com.company;

public class VanBikeParts {
    private int partNumber;
    private double salesPrice;
    private double listPrice;
    private String partName;
    private boolean sale;
    private int quantity;

    public VanBikeParts(){
        this.partName = "";
        this.partNumber = 0;
        this.listPrice = 0;
        this.salesPrice = 0;
        this.sale = false;
        this.quantity = 0;

    }
    public VanBikeParts(String partName,int partNumber,double listPrice,double salesPrice,boolean sale,int Quantity) {
        this.partNumber = partNumber;
        this.salesPrice = salesPrice;
        this.listPrice = listPrice;
        this.partName = partName;
        this.sale = sale;
        this.quantity = Quantity;

    }
    public int getPartNumber(){
        return partNumber;
    }
    public void setPartNumber(int partNumber){
        this.partNumber = partNumber;
    }

    public String getPartName() {
        return partName;
    }
    public void setPartName(String partName){
        this.partName = partName;
    }

    public double getSalesPrice() {
        return salesPrice;
    }
    public void setSalesPrice(double salesPrice){
        this.salesPrice = salesPrice;
    }

    public double getListPrice() {
        return listPrice;
    }
    public void setListPrice(double listPrice){
        this.listPrice = listPrice;
    }

    public boolean getSale() {
        return sale;
    }
    public void setSale(boolean sale){
        this.sale = sale;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    @Override public String toString(){
        return partName+"," + partNumber+"," + listPrice+"," + salesPrice+"," + sale+"," + quantity;
    }
}

