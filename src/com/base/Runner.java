package com.base;

import com.base.enums.Snacks;
import com.base.models.Snack;
import com.base.models.SnackVM;

import java.util.Scanner;
import java.util.Vector;

public class Runner {
    static SnackVM snackVM;
    final int SIZE = 5;

    public Runner() {
        snackVM = new SnackVM();
    }

    public void fillMachine() {
        Snacks[] allSnacks = Snacks.values();
        Vector< Vector<Snack>> snacksInMachine = this.snackVM.getSnacksInMachine();
        snacksInMachine.setSize(SIZE);

        for (int row = 0; row < SIZE; row++) {
            snacksInMachine.set(row, new Vector<Snack>());
            snacksInMachine.get(row).setSize(SIZE);

            for (int col = 0; col < SIZE; col++) {
                snacksInMachine.get(row).set(col, allSnacks[col].getSnackName());
            }
        }
        this.snackVM.setSnacksInMachine(snacksInMachine);
    }

    public void runMachine() throws InterruptedException {
        fillMachine();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 3) {
            switch (choice) {
                case 1:
                    snackVM.displayMachineItems();
                    System.out.println();
                    break;
                case 2:
                    snackVM.purchaseItem();
                    break;
            }
            snackVM.displayMenu();
            choice = sc.nextInt();
        }
        System.exit(0);
    }
}
