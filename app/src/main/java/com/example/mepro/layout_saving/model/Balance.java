package com.example.mepro.layout_saving.model;

public class Balance {
    private double mainBalance;
    private double savingsBalance;

    public Balance(double mainBalance, double savingsBalance) {
        this.mainBalance = mainBalance;
        this.savingsBalance = savingsBalance;
    }

    public double getMainBalance() {
        return mainBalance;
    }

    public void setMainBalance(double mainBalance) {
        this.mainBalance = mainBalance;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(double savingsBalance) {
        this.savingsBalance = savingsBalance;
    }
}
