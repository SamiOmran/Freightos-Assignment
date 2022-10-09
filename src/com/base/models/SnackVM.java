package com.base.models;

import com.base.enums.Coins;
import com.base.enums.Notes;
import com.base.enums.Snacks;
import com.base.interfaces.VendingMachines;
import javafx.util.Pair;

import java.util.Scanner;
import java.util.Vector;

public class SnackVM implements VendingMachines {
    private Coins[] coins = Coins.values();
    private Notes[] notes = Notes.values();

    private static Vector<Pair<String, Double>> allTypesOfMoney;

    private Vector< Vector<Snack> > snacksInMachine;

    private static int balance;

    public SnackVM() {
        this.balance = 0;
        this.allTypesOfMoney = new Vector<Pair<String, Double>>();
        this.snacksInMachine = new Vector< Vector<Snack> >();

        // fill the vector of pairs key and value
        for (int i = 0, j = 0; i < coins.length || j < notes.length; i++, j++) {
            if (j < notes.length)
                allTypesOfMoney.add(notes[j].getNotes());
            allTypesOfMoney.add(coins[i].getCoins());
        }

        // generate snack in the machine
        fillMachine();
    }

    void fillMachine() {
        Snacks[] allSnacks = Snacks.values();
        snacksInMachine.setSize(5);

        for (int row = 0; row < 5; row++) {
            snacksInMachine.set(row, new Vector<Snack> ());
            snacksInMachine.get(row).setSize(5);

            for (int col = 0; col < 5; col++) {
                snacksInMachine.get(row).set(col, allSnacks[col].getSnackName());
            }
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("Welcome to special 'Snack Vending Machine', here is the menu: \n" +
                "1. Display available snacks \n" +
                "2. Purchase a snack \n" +
                "3. Exit \n" +
                "You can choose what you want from it by entering index of your choice");
    }

    @Override
    public void displayMachineItems() {
        for (int i = 1; i <= 5; i++) {
            System.out.print(i+"\t\t"+"  ");
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= 5; j++) {
                Snack snack = snacksInMachine.get(i).get(j-1);
                System.out.print(snack.getName() + "  ");
            }
            System.out.println();
        }
    }

    @Override
    public void purchaseItem() {
        System.out.println("Please enter the snack's column number");
        Scanner sc = new Scanner(System.in);
        int snackCol = sc.nextInt() - 1;

        Snack snack = snackAvailable(snackCol);

        while (snack == null) {
            snackCol = sc.nextInt() - 1;
            snack = snackAvailable(snackCol);
        }

        if (snack.getQuantity() != 0) {
            double price = snack.getPrice();
            double customerMoney = insertMoney();

            if (customerMoney < price) {
                System.out.println("The money you entered is not enough.");
            } else if (customerMoney > price){
                dispense(customerMoney, price);
            } else {
                System.out.println("Thanks for using our Machine!");
            }
            this.balance+= price;
        }
    }

    @Override
    public double insertMoney() {
        System.out.println("Please enter money in this format:\n" +
                "* 10c as for 10 cents\n" +
                "* 20c as for 20 cents\n" +
                "* 50c as for 50 cents\n" +
                "* 1 as for 1 dollar\n" +
                "* 20 as for 20 dollars\n" +
                "* 50 as for 50 dollars\n");

        System.out.println("Keep entering money either coins or notes or both in separated lines, when exit enter '0' ");

        Scanner sc = new Scanner(System.in);
        String money = sc.nextLine();
        double totalMoney = 0;

        while (!money.equalsIgnoreCase("0")) {
            if (!validateMoney(money)) {
                System.out.println("The money you entered is not familiar with the machine");
            } else {
                totalMoney+= convertMoneyToNumber(money);
            }
            money = sc.nextLine();
        }
        return totalMoney;
    }

    double convertMoneyToNumber(String customerMoney) {
        double money = 0;
        for (int i = 0; i < allTypesOfMoney.size(); i++) {
            Pair<String, Double> pair = allTypesOfMoney.get(i);
            if (pair.getKey().equalsIgnoreCase(customerMoney)) {
                money = pair.getValue();
            }
        }
        System.out.println("You now entered " + money);

        return money;
    }

    Snack snackAvailable(int snackCol) {
        try {
            Snack snack = snacksInMachine.get(snackCol).get(snackCol);
            if (snack.getQuantity() != 0) {
                System.out.println(snack.getName() + " is available with price: " + snack.getPrice());
            } else {
                System.out.println(snack.getName() + " is not available");
            }
            return snack;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("The choice you entered is not found, please re-enter your choice");
            return null;
        }
    }

    @Override
    public boolean validateMoney(String money) {
        for (int i = 0; i < allTypesOfMoney.size(); i++) {
            if (allTypesOfMoney.get(i).getKey().equalsIgnoreCase(money)) return true;
        }
        return false;
    }

    @Override
    public double dispense(double totalMoney, double price) {
        double netMoney = totalMoney - price;
        System.out.println("remaining money for you is " + netMoney );
        return netMoney;
    }
}
