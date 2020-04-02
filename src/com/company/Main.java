package com.company;

public class Main{

    public static void main(String[] args){
        UserInterface choices = new UserInterface();
        Methods opt = new Methods();
        opt.initialInventory();
        choices.userChoices(opt.bikes, opt.numbers, opt.names);

    }
}
