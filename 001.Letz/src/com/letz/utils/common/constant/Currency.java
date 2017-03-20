package com.letz.utils.common.constant;

public enum Currency {
    WON("�썝"), DOLLAR("$"), YEN("占�"), YUAN("Y");

    private String display;

    private Currency(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return display;
    }
}
