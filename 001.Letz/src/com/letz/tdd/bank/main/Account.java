package com.letz.tdd.bank.main;

public class Account {
    private int blance;

    public Account(int money) {
        // TODO Auto-generated constructor stub
        this.blance = money;
    }

    public int getBlance() {
        // TODO Auto-generated method stub
        return this.blance;
    }

    public void deposit(int money) {
        // TODO Auto-generated method stub
        this.blance += money;
    }

    public void withdraw(int money) {
        // TODO Auto-generated method stub
        this.blance -= money;
    }

}
