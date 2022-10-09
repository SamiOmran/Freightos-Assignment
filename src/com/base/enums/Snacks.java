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
                return new Snack("Kinder", (float)0.5, 5);
            case KITKAT:
                return new Snack("KitKat", (float)0.6, 5);
            case SNICKERS:
                return new Snack("Snickers", (float)0.1, 5);
            case BONBON:
                return new Snack("BonBon", 1, 5);
            case MARS:
                return new Snack("Mars", 5, 5);
            default:
                return new Snack("Empty slot", 0, 0);
        }
    }
}
