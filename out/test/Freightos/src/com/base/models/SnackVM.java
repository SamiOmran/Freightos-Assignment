package com.base.models;

import com.base.enums.Coins;
import com.base.enums.Notes;
import com.base.interfaces.VendingMachines;
import javafx.util.Pair;

import java.util.Scanner;
import java.util.Vector;

public class SnackVM implements VendingMachines {
    private Coins[] coins = Coins.values();
    private Notes[] notes = Notes.values();
    private static Vector<Pair<String, Double>> allTypesOfMoney;
    private Vector< Vector<Snack> > snacksInMachine;
    private final long SLEEP = 1500L;
    private final int SIZE = 5;

    public Vector<Vector<Snack>> getSnacksInMachine() {
        return snacksInMachine;
    }

    public void setSnacksInMachine(Vector<Vector<Snack>> snacksInMachine) {
        this.snacksInMachine = snacksInMachine;
    }

    public SnackVM() {
        this.allTypesOfMoney = new Vector<Pair<String, Double>>();
        this.snacksInMachine = new Vector< Vector<Snack> >();

        // fill the vector money of pairs key and value
        for (int i = 0, j = 0; i < coins.length || j < notes.length; i++, j++) {
            if (j < notes.length)
                allTypesOfMoney.add(notes[j].getNotes());
            allTypesOfMoney.add(coins[i].getCoins());
        }
    }

    @Override
    public void displayMenu() throws InterruptedException {
        System.out.println("Welcome to special 'Snack Vending Machine', here is the menu: \n" +
                "1. Display available snacks \n" +
                "2. Purchase a snack \n" +
                "3. Exit \n" +
                "You can choose what you want from it by entering index of your choice");
        Thread.sleep(SLEEP);
    }

    @Override
    public void displayMachineItems() throws InterruptedException {
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i+"\t\t"+"  ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Snack snack = snacksInMachine.get(i).get(j);
                if (snack.getQuantity() > i) {
                    System.out.print(snack.getName() + "  ");
                } else {
                    System.out.print("EMPTY\t");
                }
            }
            System.out.println();
        }
        Thread.sleep(SLEEP);
    }

    @Override
    public void purchaseItem() throws InterruptedException {
        System.out.println("Please enter the snack's row and column in separated lines");
        Scanner sc = new Scanner(System.in);
        int snackRow = sc.nextInt() - 1;
        int snackCol = sc.nextInt() - 1;
        Snack snack = checkAvailableSnack(snackRow, snackCol);

        while (snack == null) {
            snackRow = sc.nextInt() - 1;
            snackCol = sc.nextInt() - 1;
            snack = checkAvailableSnack(snackRow, snackCol);
        }

        if (snack.getQuantity() != 0) {
            double price = snack.getPrice();
            double customerMoney = insertMoney();

            customerMoney = roundDigits(customerMoney);
            price = roundDigits(price);

            if (customerMoney < price) {
                System.out.println("The money you entered is not enough.");
            } else if (customerMoney > price){
                dispense(customerMoney, price);
            } else {
                System.out.println("Thanks for using our Machine!\n");
            }
        }
        Thread.sleep(SLEEP);
        snack.removeSnack();
    }

    @Override
    public double insertMoney() throws InterruptedException {
        System.out.println("Please enter money in this format:\n" +
                "* 10c as for 10 cents\n" +
                "* 20c as for 20 cents\n" +
                "* 50c as for 50 cents\n" +
                "* 1 as for 1 dollar\n" +
                "* 20 as for 20 dollars\n" +
                "* 50 as for 50 dollars\n");
        Thread.sleep(SLEEP);

        System.out.println("Keep entering money either coins or notes or both in separated lines, when exit enter '0' ");

        Scanner sc = new Scanner(System.in);
        String money = sc.nextLine();
        double totalMoney = 0;

        while (!money.equalsIgnoreCase("0")) {
            if (!validateMoney(money)) {
                System.out.println("The money you entered is not familiar with the machine");
            } else {
                totalMoney = roundDigits(totalMoney) + convertMoneyToNumber(money);
                System.out.println("Total money entered: " + totalMoney + "$");
            }
            money = sc.nextLine();
        }
        return totalMoney;
    }

    public double convertMoneyToNumber(String customerMoney) {
        double money = 0;
        for (int i = 0; i < allTypesOfMoney.size(); i++) {
            Pair<String, Double> pair = allTypesOfMoney.get(i);
            if (pair.getKey().equalsIgnoreCase(customerMoney)) {
                money = pair.getValue();
                money = roundDigits(money);
                break;
            }
        }
        System.out.println("You now entered " + money);

        return money;
    }

    Snack checkAvailableSnack(int snackRow, int snackCol) {
        try {
            Snack snack = snacksInMachine.get(snackRow).get(snackCol);
            if (snack.getQuantity() != 0) {
                System.out.println(snack.getName() + " is available with price: " + snack.getPrice() + "\n");
            } else {
                System.out.println(snack.getName() + " is not available\n");
            }
            return snack;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("The choice you entered is not found, please re-enter your choice\n");
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
        double netMoney = roundDigits(totalMoney - price);
        System.out.println("remaining money for you is " +  netMoney);
        return netMoney;
    }

    double roundDigits(double number) {
        return Math.round(number*1e6) / 1e6;
    }
}
