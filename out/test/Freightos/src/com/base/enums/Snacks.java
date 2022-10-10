package com.base.enums;

import com.base.models.Snack;

public enum Snacks {
    KINDER,
    KITKAT,
    SNICKERS,
    BONBON,
    MARS;

    public Snack getSnackName() {
        switch (this) {
            case KINDER:
                return new Snack("Kinder",  0.5, 2);
            case KITKAT:
                return new Snack("KitKat", 0.6, 5);
            case SNICKERS:
                return new Snack("Snickers", 0.1, 3);
            case BONBON:
                return new Snack("BonBon", 1, 7);
            default:
                return new Snack("Mars", 5, 4);
        }
    }
}
