package com.base.tests;

import com.base.enums.Snacks;
import com.base.models.Snack;
import com.base.models.SnackVM;
import org.junit.jupiter.api.*;

import java.util.Vector;

class SnackVMTest {
    static Vector<Vector<Snack>> snacks;
    static SnackVM machine;

    @BeforeAll
    public static void fill2DArray() {
        Snacks[] allSnacks = Snacks.values();
        machine = new SnackVM();

        snacks = machine.getSnacksInMachine();
        snacks.setSize(5);

        for (int row = 0; row < 5; row++) {
            snacks.set(row, new Vector<Snack>());
            snacks.get(row).setSize(5);

            for (int col = 0; col < 5; col++) {
                snacks.get(row).set(col, allSnacks[col].getSnackName());
            }
        }
        machine.setSnacksInMachine(snacks);
    }

    @AfterEach
    public void displayMachine() {
        for (int i = 1; i <= 5; i++) {
            System.out.print(i+"\t\t"+"  ");
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Snack snack = snacks.get(i).get(j);
                if (snack.getQuantity() > i) {
                    System.out.print(snack.getName() + "  ");
                } else {
                    System.out.print("EMPTY\t");
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testConvertMoneyToNumber() {
        double expected = 50;
        double actual = machine.convertMoneyToNumber("50");
        Assertions.assertEquals(expected, actual);
    }

}