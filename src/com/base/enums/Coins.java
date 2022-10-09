package com.base.enums;

import javafx.util.Pair;

public enum Coins {
    TENc,
    TWENTYc,
    FIFTYc,
    DOLLAR;

    public Pair<String, Double> getCoins() {
        Pair<String, Double> pair;
        switch (this) {
        case TENc:
            return new Pair<>("10c", 0.1);
        case TWENTYc:
            return new Pair<>("20c", 0.2);
        case FIFTYc:
            return new Pair<>("50c", 0.5);
        default:
            return new Pair<>("1", 1.0);        }
    }

}
