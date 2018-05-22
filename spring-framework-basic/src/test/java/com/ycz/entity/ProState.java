package com.ycz.entity;

public enum ProState {

    AWAITING(0), PASS(1), REFUSAL(2);

    private int value;

    ProState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
