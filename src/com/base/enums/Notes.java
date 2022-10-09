package com.base.enums;

import javafx.util.Pair;

public enum Notes {
    TWENTYd,
    FIFTYd;

    public Pair<String, Double> getNotes() {
        switch (this) {
            case TWENTYd:
                return new Pair<>("20", 20.0);
            default:
                return new Pair<>("50", 50.0);
        }
    }
}
