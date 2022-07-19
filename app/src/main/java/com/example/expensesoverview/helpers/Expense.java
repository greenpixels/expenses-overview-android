package com.example.expensesoverview.helpers;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {
    private float amount;
    private Date date;

    public Expense(float amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
