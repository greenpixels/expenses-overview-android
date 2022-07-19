package com.example.expensesoverview.helpers;

public class ExpenseSlice {
    private double opacity;
    private float percentage;
    private String category;
    private int colour;
    private boolean selected;

    public ExpenseSlice(float percentage, String category, int colour) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public void setSelected(boolean isSelected) {
        this.selected = isSelected;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }
}
