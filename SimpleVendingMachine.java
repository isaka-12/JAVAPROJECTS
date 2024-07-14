package JAVAPRACTICE;

import java.util.Scanner;

public class SimpleVendingMachine {
    public static void main(String[] args) {
        // Display the title of the vending machine
        System.out.println("==========SIMPLE VENDING MACHINE=========="); 
        Scanner myscanner = new Scanner(System.in);
        int gum = 0;
        int chocolate = 0;
        int popcon = 0;
        int juice = 0;
        while (true) {
            System.out.println("Please select an item bellow!!! ");
            System.out.println("1. Get gum");
            System.out.println("2. Get chocolate");
            System.out.println("3. Get popcon");
            System.out.println("4. Get juice");
            System.out.println("5. Display total sold so far.");
            System.out.println("6. Exit");

            int myoption = myscanner.nextInt();
            //initialize the variables
           

            switch (myoption) {
                case 1:
                    System.out.println("Here is your gum");
                    gum++;
                    break;

                case 2:
                    System.out.println("Here is your chocolate");
                    chocolate++;
                    break;

                case 3:
                    System.out.println("Here is your popcon");
                    popcon++;
                    break;

                case 4:
                    System.out.println("Here is your juice");
                    juice++;
                    break;

                case 5:
                    System.out.println(gum + " items of gum sold");
                    System.out.println(chocolate + " items of chocolate sold");
                    System.out.println(popcon + " items of popcon sold");
                    System.out.println(juice + " items of juice sold");
                    break;

                case 6:
                    System.out.println("Thank you for using the vending machine");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error, option 1-6 only!");
                    break;
            }
        }
        

    }
}
