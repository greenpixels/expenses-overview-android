package com.example.expensesoverview.helpers;

public class ExpenseSlice {
    private float percentage;
    private Category category;
    private int colour;

    public ExpenseSlice(float percentage, Category category, int colour) {
        this.percentage = percentage;
        this.category = category;
        this.colour = colour;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }
}
