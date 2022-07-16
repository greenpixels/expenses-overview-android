package com.example.expensesoverview.helpers;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Expenses implements Serializable {
    private HashMap<String, ArrayList<Expense>> map = new HashMap<>();
    private int month;
    private int year;

    public Expenses(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Expense> getByCategory(String category) {
        return map.get(category);
    }

    public float getSumFromArray(String[] categories) {
        float sum = 0;
        for(String category : categories) {
            ArrayList<Expense> expenses = getByCategory(category);
            if(expenses == null) continue;
            for(Expense expense : expenses ) {
                sum+=expense.getAmount();
            }
        }
        return sum;
    }

    public float getSumFromCategory(String category) {
        float sum = 0;
        ArrayList<Expense> expenses = getByCategory(category);
        if(expenses == null) return 0;
        for(Expense expense : expenses ) {
            sum+=expense.getAmount();
        }
        return sum;
    }

    public void add(float amount, String category, Date date) {
        ArrayList<Expense> list = map.get(category);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(new Expense(amount, date));
        map.put(category, list);
    }

    public static Expenses loadExpensesData(int month, int year, Context context) {
        try {
            FileInputStream fis = context.openFileInput(month+"_"+year+"_"+"expenses");
            ObjectInputStream is = new ObjectInputStream(fis);
            Expenses expenses = (Expenses) is.readObject();
            is.close();
            fis.close();

            return expenses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean saveExpensesData(int month, int year, Context context, Expenses expenses) {
        try {
            FileOutputStream fos = context.openFileOutput(month+"_"+year+"_"+"expenses", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(expenses);
            fos.close();
            oos.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
