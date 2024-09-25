/* CS2401
 * Files needed to complete Lab 3:
 * 	- Node.java
 * 	- LinkedList.java
 *  - Lab3_Lastname.java --- the java file of your program. 
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class lab3Main{
    /**
     * TODO: Make a method that takes in a file name and returns a 2D String array
     * containing the value of every row in the "itemList.csv"
     */
    public static void printTest(String[] arr){
        System.out.println("This is the test value " );
        for(int i = 0; i <arr.length; i++){
            System.out.println(arr[i]);
        }
    }
    public static String[][] scanItems() throws FileNotFoundException{
        File filePath = new File( "D:\\PM\\VSC\\Lab3\\CS2401_LAB3\\CS2401_LAB3\\itemList.csv");
        int[] rowsCols = countLine(filePath);
        String[][] itemArray = new String[rowsCols[0]][rowsCols[1]];
        Scanner i02 = new Scanner(filePath);
        String trash = i02.nextLine();

        while (i02.hasNextLine()) {
            for(int i = 0; i <itemArray.length; i++){
                itemArray[i] = i02.nextLine().split(",");
                //printTest(itemArray[i]);
            }
        }
        return itemArray;
    }

    //method for reading the num of rows and cols in the file
    public static int[] countLine(File filePath) throws FileNotFoundException{
        Scanner i01 = new Scanner(filePath);
        int[] counter = {0,0};
        while (i01.hasNextLine()) { 
            counter[0]+=1; 
            String line = i01.nextLine();
            String[] cols = line.split(",");
            if (cols.length > counter[1]) {
                counter[1] = cols.length; 
            }
        }
        counter[0]-=1 ; //there is a trash line - again.  
        return counter;
    }


    public static void main(String[] args) throws Exception{
        //Read CSV file
        String[][] shopArray = scanItems();

        // Define your inventory
        int option = 1;
        InventoryLL inventory = new InventoryLL();
         while(option!=6){
            System.out.println("");
            System.out.println("_______________________________________________");
            System.out.println("__        __   _                ");                           
            System.out.println("\\ \\      / /__| | ___ ___  _ __ ___   ___ ");
            System.out.println(" \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\");
            System.out.println("  \\ V  V /  __/ | (_| (_) | | | | | |  __/");
            System.out.println("   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|");
            System.out.println("_______________________________________________");
            System.out.println("");
            System.out.println("           Welcome to Hell              ");
            System.out.println("");
            System.out.println("Please select one of the following options:");
            System.out.println("1) View shop");
            System.out.println("2) View inventory");
            System.out.println("3) Sell item");
            System.out.println("4) Buy item");
            System.out.println("5) View item stats");
            System.out.println("6) Exit");

            // TODO: Create the Start Menu
            Scanner i02 = new Scanner(System.in);
            option = i02.nextInt();
            switch(option){
                case 1: //display array
                    displayShop(shopArray);
                    break;
                case 2: //display inven
                    inventory.displayItems();
                    System.out.println("this is the end of your inventory ");
                    break;
                case 3: //remove item
                    Scanner i03 = new Scanner(System.in);
                    System.out.println("Enter the name of the item you want to remove: ");
                    inventory.removeItem(i03.nextLine().trim());
                    break;

                case 4: //add item
                    Scanner i04 = new Scanner(System.in);
                    System.out.println("Please enter name: ");
                    String itemName =   i04.nextLine().trim(); //this is string
                    for(int i = 0; i<shopArray.length; i++){
                        if(shopArray[i][1].equals(itemName)){
                            System.out.println("Item found, adding to inventory.");
                            inventory.addToInventory(shopArray[i]);//put the string array into the method. 
                        }
                    }

                    break;

                case 5: //enter index, return the correct item. 
                    String[] itemArray;
                    Scanner i05 = new Scanner(System.in);
                    System.out.println("Please enter the index of the item you want to view.");
                    itemArray = inventory.getFromInventory(i05.nextInt());
                    if (itemArray!=null) {
                        for (String attributes : itemArray) {
                            System.out.print(attributes + " | ");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Probably worst code ever, but yeah, I'm still learning. ");
                    break;
                default:
                    System.out.println("Please, stop trying to break my code, I beg for your mercy.");
                    break; 

            }

        }
    }

    /*
     * 
     * 
     * DO NOT CHANGE, DISPLAYS SHOP
     * 
     * 
     * 
     */

    public static void displayShop(String[][] shop) {
    // Define column widths for each column (adjust these values to fit your data)
    int nameWidth = 32;
    int rarityWidth = 17;
    int abilityWidth = 82;
    int hpWidth = 8;
    int costWidth = 7;
    
    System.out.println();
    // Print the top border
    System.out.println("+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" 
                         + "-".repeat(abilityWidth) + "+" + "-".repeat(hpWidth) + "+" + "-".repeat(costWidth) + "+");

    // Print the header row
    System.out.printf("| %-30s | %-15s | %-80s | %-5s | %-5s |%n", 
                      "Name", "Rarity", "Magical Abilities", "HP", "Cost");

    // Print the separator after the header
    System.out.println("+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" 
                         + "-".repeat(abilityWidth) + "+" + "-".repeat(hpWidth) + "+" + "-".repeat(costWidth) + "+");

    // Loop through the 2D array and print each row
    for (int i = 0; i < shop.length; i++) {
        if(shop[i][1] == null){
            i++;
        }
        else {  // Ensure the shop row is not null
            System.out.printf("| %-30s | %-15s | %-80s | %-5s | %-5s |%n", 
                              shop[i][1] ,    // Name
                              shop[i][2] ,    // Rarity
                              shop[i][3] ,    // Magical Abilities
                              shop[i][4] ,    // HP
                              shop[i][5]      // Cost
            );
        }
    }

    // Print the bottom border
    System.out.println("+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" 
                         + "-".repeat(abilityWidth) + "+" + "-".repeat(hpWidth) + "+" + "-".repeat(costWidth) + "+");
    System.out.println();
    }
     /*
     * 
     * 
     * DO NOT CHANGE, DISPLAYS SHOP
     * 
     * 
     * 
     */

}






