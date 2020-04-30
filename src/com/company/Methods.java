package com.company;

import java.io.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Methods{
    int num;
    String[] vanparts;
    List<BikeParts> bikes = new ArrayList<>();
    List<VanBikeParts> vanbikes = new ArrayList<>();
    String[] user;
    String[] startVan;
    List<String> vannames = new ArrayList<>();
    List<Integer> vannumbers = new ArrayList<>();
    List<String> names = new ArrayList<>();
    List<Integer> numbers = new ArrayList<>();
    List<String> vanNames = new ArrayList<>();
    List<Integer> vanNumbers = new ArrayList<>();
    String[] userr;

    public void readFile() {
        initialInventory();
        Scanner in = new Scanner(System.in);
        String line;
        BufferedReader reader = null;
        System.out.println("Please enter the name of your file:");
        String file = in.nextLine();
        try {
            reader = new BufferedReader(new FileReader(file + ".txt"));
            line = reader.readLine();
            while (line != null) {
                writeFile(line, bikes);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Manually enter a part and decides if the part is already in the warehouse
    public void manuEnter(List<BikeParts> bikes){
        Scanner in = new Scanner(System.in);
        Scanner scnr = new Scanner(System.in);

        System.out.println("How many parts would you like to add?");
        int num = scnr.nextInt();

        //List<BikeParts> bikes = new ArrayList<>();
        //String[] user;
        boolean contain = false;
        System.out.println("Please enter your information");
        for (int i = 0; i < num; i++) {
            String userInput = in.nextLine();
            user = userInput.split(",");
            for (int l = 0; l < bikes.size(); l++) {
                if (user[0].equalsIgnoreCase(bikes.get(l).getPartName())) {
                    bikes.get(l).setListPrice(Double.parseDouble(user[2]));
                    bikes.get(l).setSalesPrice(Double.parseDouble(user[3]));
                    bikes.get(l).setSale(Boolean.parseBoolean(user[4]));
                    bikes.get(l).setQuantity(bikes.get(l).getQuantity() + Integer.parseInt(user[5]));
                    contain = true;
                }
            }
                if (!contain) {
                    bikes.add(new BikeParts(user[0], Integer.parseInt(user[1]), Double.parseDouble(user[2]), Double.parseDouble(user[3]), Boolean.parseBoolean(user[4]), Integer.parseInt(user[5])));
                }
                contain = false;
        }
    }
    //Does exactly that..quits the program
    public void userQuit(List<BikeParts> bikes){
        writeEnd(this.bikes);
        System.out.println("Have a nice day!");
    }

    // Just a simple message to ask the user if they would like to perform another action
    public void goAgain(List<BikeParts> bikes, List<Integer> numbers, List<String> names){
        Scanner in = new Scanner(System.in);
        UserInterface user = new UserInterface();
        System.out.println("Would you like to do something else?\nYes or No?");
        String ans = in.nextLine();
        if(ans.equalsIgnoreCase("Yes")){
            user.userChoices(bikes, numbers, names);
        }
        else{
            userQuit(bikes);
        }
    }

    //Writes the original DB from the inventory files
    private void writeFile(String line, List<BikeParts> a){
        //Collections.sort(names);
        userr = line.split(",");
        for (int i = 0; i < names.size(); i++) {
            if (userr[0].equalsIgnoreCase(names.get(i))) {
                bikes.get(i).setQuantity(bikes.get(i).getQuantity() + Integer.parseInt(userr[5]));
                break;
            }
            else {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("warehouseDB.txt", true))) {
                    writer.write(line);
                    writer.newLine();
                }
                catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        //initialInventory();
        writeEnd(a);
    }

    //Writes the warehouse DB again in case any changes were made i.e Sell
    private void writeEnd(List<BikeParts> line){

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("warehouseDB.txt"))) {
            for(int i = 0; i<line.size(); i++){
                String str = line.get(i).toString();
                writer.write(str);
                writer.newLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //Creates a variable for the number of parts in the warehouse to make counting easy
    public int numberOfParts(){
        String parts;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("warehouseDB.txt"));
            parts = reader.readLine();
            while (parts != null) {
                num++;
                parts = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return num;
    }

    //Runs the inventory of the warehouse from the start
    public void initialInventory(){
        String linee;
        BufferedReader reader = null;
        num = numberOfParts();
        try {
            reader = new BufferedReader(new FileReader("warehouseDB.txt"));
            linee = reader.readLine();
            if(linee == null){
                noInventory();
            }
            while (linee != null) {
                for (int i = 0; i < num; i++) {
                    user = linee.split(",");
                    bikes.add(new BikeParts(user[0],Integer.parseInt(user[1]), Double.parseDouble(user[2]), Double.parseDouble(user[3]), Boolean.parseBoolean(user[4]), Integer.parseInt(user[5])));
                    names.add(user[0]);
                    numbers.add(Integer.parseInt(user[1]));
                    linee = reader.readLine();
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Runs the inventory of the warehouse from the start
    public void noInventory(){
        String linee;
        BufferedReader reader = null;
        num = numberOfParts();
        try {
            reader = new BufferedReader(new FileReader("inventory.txt"));
            linee = reader.readLine();
            while (linee != null) {
                    user = linee.split(",");
                    bikes.add(new BikeParts(user[0],Integer.parseInt(user[1]), Double.parseDouble(user[2]), Double.parseDouble(user[3]), Boolean.parseBoolean(user[4]), Integer.parseInt(user[5])));
                    names.add(user[0]);
                    numbers.add(Integer.parseInt(user[1]));
                    linee = reader.readLine();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Shows a parts price and name if the part is in stock
    public void showPart(List<BikeParts> arr){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Part Name: ");
        String name = in.next();
        boolean contains = false;
        for(int i = 0; i<arr.size();i++){
            if(arr.get(i).getPartName().equals(name)){
                displayPart(arr.get(i));
                contains = true;
            }
        }
        if(!contains){
            System.out.println("Error: Part doesn't exist");
            showPart(arr);
        }
    }

    //A method that can be used in multiple methods, makes showing the name and price easier
    private void displayPart(BikeParts bp){
        System.out.println("Part Name: " +bp.getPartName());
        if(bp.getSale()){
            System.out.println("Sales Price: "+bp.getSalesPrice());
        }else{
            System.out.println("List Price: "+ bp.getListPrice());
        }
    }

    //Sells a part and decreases the quantity of the part and if their is only one part left it removes the part
    public void sellPart(List<BikeParts> arr){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Part Number: ");
        boolean contains = false;
        int number = in.nextInt();
        for(int i = 0; i<arr.size();i++){
            if(arr.get(i).getPartNumber() == number) {
                displayPart(arr.get(i));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                //System.out.println(System.currentTimeMillis());
                contains = true;
                if(arr.get(i).getQuantity() > 0){
                    arr.get(i).setQuantity(arr.get(i).getQuantity() - 1);
                    break;
                }
                else if(arr.get(i).getQuantity() < 0){
                    arr.remove(i);
                    break;
                }
            }
        }
        if(!contains){
            System.out.println("Error: Part doesn't exist");
            sellPart(arr);
        }
    }
    public void sortNumber(List<BikeParts> bikes, List<Integer> numbers, String warehouse){
        if(warehouse.equalsIgnoreCase("main warehouse")) {
            Collections.sort(numbers);
            int z = 0;
            for(int i = 0; z < numbers.size(); i++){
                if(numbers.get(z).equals(bikes.get(i).getPartNumber())){
                    System.out.println(bikes.get(i).toString());
                    z++;
                    i = -1;
                }
            }
        }
        if(!warehouse.equalsIgnoreCase("main warehouse")){
            Collections.sort(vannumbers);
            int z = 0;
            for(int i = 0; z < vannumbers.size(); i++){
                if(vannumbers.get(z).equals(vanbikes.get(i).getPartNumber())){
                    System.out.println(vanbikes.get(i).toString());
                    z++;
                    i = -1;
                }
            }
        }
    }
    public void sortName(List<BikeParts> bikes, List<String> names, String warehouse){
        if(warehouse.equalsIgnoreCase("main warehouse")) {
            Collections.sort(names);
            int z = 0;
            for (int i = 0; z < names.size(); i++) {
                if (names.get(z).equalsIgnoreCase(bikes.get(i).getPartName())) {
                    System.out.println(bikes.get(i).toString());
                    z++;
                    i = -1;
                }
            }
        }
        if(!warehouse.equalsIgnoreCase("main warehouse")){
                Collections.sort(vannames);
            int z = 0;
            for (int i = 0; z < vannames.size(); i++) {
                if (vannames.get(z).equalsIgnoreCase(vanbikes.get(i).getPartName())) {
                    System.out.println(vanbikes.get(i).toString());
                    z++;
                    i = -1;
                }
            }
            }
    }

    //PROJECT TWO CODE

    public void addVan() {
        Scanner in = new Scanner(System.in);
        System.out.println("What would you like to call this van?");
        String file = in.next();
        try(PrintWriter newFile = new PrintWriter(file+ ".txt")) {
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void readVanFile(List<BikeParts> bikes) {
        Scanner in = new Scanner(System.in);
        String line;
        String whichInv;
        BufferedReader reader = null;
        System.out.println("Please enter the name of your van delivery file:");
        String file = in.nextLine();
        String van;
        try {
            reader = new BufferedReader(new FileReader(file + ".txt"));
            line = reader.readLine();
            startVan = line.split(",");
            whichInv = startVan[0];
            van = startVan[1];

            while (line != null) {
                line = reader.readLine();
                if(line!= null) {
                    parseInvofVan(line);
                }
            }
            if(!whichInv.equalsIgnoreCase("mainwarehouse")){
                VaninitialInventory(whichInv);
                moveInv(bikes,vanNames,vanNumbers,van, whichInv);
            }
            if(whichInv.equalsIgnoreCase("mainwarehouse")){
                moveInv(bikes,vanNames,vanNumbers,van, whichInv);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void writeVans(String van, List<BikeParts> bikes, int i) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(van + ".txt", true))) {
            writer.write(bikes.get(i).toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeVanParts(String van, List<VanBikeParts> vanbikes, int i) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(van + ".txt", true))) {
            writer.write(vanbikes.get(i).toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void VanwriteEnd(List<VanBikeParts> line, String whichInv){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(whichInv + ".txt"))) {
            for(int i = 0; i<line.size(); i++){
                String str = line.get(i).toString();
                writer.write(str);
                writer.newLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void parseInvofVan(String line){
        vanparts = line.split(",");
        vanNames.add(vanparts[0]);
        vanNumbers.add(Integer.parseInt(vanparts[1]));
    }
    public void moveInv(List<BikeParts> bikes, List<String> vanNames, List<Integer> vanNumbers, String van, String whichInv){
        int z = 0;
        if(whichInv.equalsIgnoreCase("mainwarehouse")) {
            for (int i = 0; z < vanNames.size(); i++) {
                if (vanNames.get(z).equalsIgnoreCase(bikes.get(i).getPartName())) {
                    if (bikes.get(i).getQuantity() > vanNumbers.get(z)) {
                        int temp = bikes.get(i).getQuantity();
                        bikes.get(i).setQuantity(vanNumbers.get(z));
                        writeVans(van, bikes, i);
                        bikes.get(i).setQuantity(temp - vanNumbers.get(z));
                    }
                    if (bikes.get(i).getQuantity() <= vanNumbers.get(z)) {
                        writeVans(van, bikes, i);
                        bikes.get(i).setQuantity(0);
                        //bikes.remove(i);
                    }
                    z++;
                    i = 0;
                }
            }
        }
        if(!whichInv.equalsIgnoreCase("mainwarehouse")) {
            for (int i = 0; z < vanNames.size(); i++) {
                if (vanNames.get(z).equalsIgnoreCase(vanbikes.get(i).getPartName())) {
                    if (vanbikes.get(i).getQuantity() > vanNumbers.get(z)) {
                        int temp = vanbikes.get(i).getQuantity();
                        vanbikes.get(i).setQuantity(vanNumbers.get(z));
                        writeVanParts(van, vanbikes, i);
                        vanbikes.get(i).setQuantity(temp - vanNumbers.get(z));
                    }
                    if (vanbikes.get(i).getQuantity() <= vanNumbers.get(z)) {
                        writeVanParts(van, vanbikes, i);
                        vanbikes.get(i).setQuantity(0);
                        //vanbikes.remove(i);
                    }
                    z++;
                    i = 0;
                }
            }
        }
        int i = 0;
        while (i< vanbikes.size()) {
            if(vanbikes.get(i).getQuantity()>0) {
                i++;
            }
            if(vanbikes.size()<=1){
                i=0;
                if(vanbikes.get(i).getQuantity()==0) {
                    vanbikes.remove(i);
                }
                break;
            }
            if(vanbikes.get(i).getQuantity()==0) {
                vanbikes.remove(i);
                i=0;
            }
        }
        while (i< bikes.size()-1) {
            if(bikes.get(i).getQuantity()>0) {
                i++;
            }
            if(bikes.get(i).getQuantity()==0) {
                bikes.remove(i);
                i=0;
            }
        }
        VanwriteEnd(vanbikes,whichInv);
    }
    public List VaninitialInventory(String whichInv){
        String linee;
        BufferedReader reader = null;
        num = numberOfParts(whichInv);
        try {
            reader = new BufferedReader(new FileReader(whichInv + ".txt"));
            linee = reader.readLine();
            if(linee == null){
                readFile();
            }
            while (linee != null) {
                for (int i = 0; i < num; i++) {
                    user = linee.split(",");
                    vanbikes.add(new VanBikeParts(user[0],Integer.parseInt(user[1]), Double.parseDouble(user[2]), Double.parseDouble(user[3]), Boolean.parseBoolean(user[4]), Integer.parseInt(user[5])));
                    vannames.add(user[0]);
                    vannumbers.add(Integer.parseInt(user[1]));
                    linee = reader.readLine();
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return vannames;
    }
    public List VaninitialInventorysortnum(String whichInv){
        String linee;
        BufferedReader reader = null;
        num = numberOfParts(whichInv);
        try {
            reader = new BufferedReader(new FileReader(whichInv + ".txt"));
            linee = reader.readLine();
            if(linee == null){
                readFile();
            }
            while (linee != null) {
                for (int i = 0; i < num; i++) {
                    user = linee.split(",");
                    vanbikes.add(new VanBikeParts(user[0],Integer.parseInt(user[1]), Double.parseDouble(user[2]), Double.parseDouble(user[3]), Boolean.parseBoolean(user[4]), Integer.parseInt(user[5])));
                    vannames.add(user[0]);
                    vannumbers.add(Integer.parseInt(user[1]));
                    linee = reader.readLine();
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return vannumbers;
    }
    public int numberOfParts(String whichInv){
        String parts;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader( whichInv + ".txt"));
            parts = reader.readLine();
            while (parts != null) {
                num++;
                parts = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return num;
    }
}
