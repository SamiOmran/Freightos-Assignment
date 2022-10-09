package com.base.interfaces;

public interface VendingMachines {
    /**
     * Method to display the menu to the user
     */
    void displayMenu();

    /**
     * Method to display list of items stored in the machine
     */
    void displayMachineItems();

    /**
     * Method to purchase an item from the machine
     */
    void purchaseItem();

    /**
     * Method to let user enter money to pay for the item\
     * @return total money inserted
     */
    double insertMoney();

    /**
     * method to check if money that the customer inserts is same as the coins and notes stored in the machine
     * @param money that customer inserts
     * @return true if its same as coins or notes in the machine
     */
    boolean validateMoney(String money);

    /**
     * method to return the rest of money inserted from the customer
     * @return rest of money, either coins or notes
     */
    double dispense(double totalMoney, double price);
}