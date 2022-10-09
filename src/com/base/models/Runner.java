package com.base.models;

import java.util.Scanner;

public class Runner {
    static SnackVM snackVM;

    public Runner() {
        snackVM = new SnackVM();
    }

    public void runMachine() {
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
                    return;
            }
            snackVM.displayMenu();
            choice = sc.nextInt();
        }
        System.exit(0);
    }
}
