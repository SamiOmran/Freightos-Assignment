package com.base.enums;

import javafx.util.Pair;

import java.math.BigDecimal;

public enum Notes {
    TWENTYd,
    FIFTYd;

    public Pair<String, Double> getNotes() {
        switch (this) {
            case TWENTYd:
                return new Pair<>("20", 20d);
            default:
                return new Pair<>("50", 50d);
        }
    }
}
