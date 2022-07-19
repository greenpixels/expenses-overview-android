package com.example.expensesoverview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.expensesoverview.helpers.Converter;
import com.example.expensesoverview.helpers.ExpenseSlice;
import com.example.expensesoverview.helpers.Expenses;
import com.example.expensesoverview.views.PieChart;
import com.example.expensesoverview.views.PieChartLabel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public Expenses expenses;
    public int currentSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);

        Intent i = getIntent();
        if(i.getIntExtra("eMonth", -1) != -1 && i.getIntExtra("eYear", -1) != -1) {
            currentMonth = i.getIntExtra("eMonth", -1);
            currentYear = i.getIntExtra("eYear", -1);
        }
        TextView textDate = findViewById(R.id.selectedDate);
        Resources r = getResources();
        String displayText = r.getStringArray(R.array.string_array_months)[currentMonth] + ", " + currentYear;
        textDate.setText(displayText);

        this.expenses = Expenses.loadExpensesData(currentMonth, currentYear, this);
        if(expenses == null) {
            expenses = new Expenses(currentMonth, currentYear);
        }

        LinearLayout lc = findViewById(R.id.labelContainer);
        PieChart pc = findViewById(R.id.pieChart);

        Resources res = getResources();
        String[] categories = res.getStringArray(R.array.string_array_categories);
        float expensesTotal = expenses.getSumFromArray(categories);
        for(ExpenseSlice slice : pc.getSlices()) {
            PieChartLabel pcl = new PieChartLabel(this);
            pcl.setText(slice.getCategory());
            pcl.setColour(slice.getColour());
            slice.setPercentage(expenses.getSumFromCategory(slice.getCategory()) / expensesTotal * 100);
            pcl.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ViewGroup.LayoutParams params = pcl.getLayoutParams();
            params.height = 48;
            pcl.setLayoutParams(params);
            lc.addView(pcl);
        }
    }

    public void switchToDateActivity(View v) {
        Intent i = new Intent(this, DateActivity.class);
        startActivity(i);
    }

    public void switchToNewExpenseActivity(View v) {
        Intent i = new Intent(this, NewExpenseActivity.class);
        i.putExtra("ExpensesExtra", Converter.objectToString(expenses));
        startActivity(i);
    }

    public void updateSlices() {
        PieChart pc = findViewById(R.id.pieChart);
        int index = 0;
        for(ExpenseSlice slice : pc.getSlices()) {
            slice.setSelected(currentSelected == index);
            index++;
        }
    }
}



