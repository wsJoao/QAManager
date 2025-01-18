package com.jppin.quickaccountmanager.models;

public class Transaction {
    private int amount;

    public Transaction() {
        this.amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Amount to add must be greater than zero");
        }
        amount += value;
    }

    public void clearAmount() {
        amount = 0;
    }
}
