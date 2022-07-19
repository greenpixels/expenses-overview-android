package com.example.expensesoverview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.expensesoverview.helpers.Converter;
import com.example.expensesoverview.helpers.Expense;
import com.example.expensesoverview.helpers.Expenses;

import java.util.Calendar;
import java.util.Date;

public class NewExpenseActivity extends AppCompatActivity {
    Expenses expenses;
    public int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        Intent i = getIntent();
        String base64Object = i.getStringExtra("ExpensesExtra");
        expenses = (Expenses) Converter.stringToObject(base64Object);
        CalendarView calView = findViewById((R.id.calendarView));
        // Why the heck do I need to write my own listener here, android?
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, day);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.YEAR, year);
                calendarView.setDate(cal.getTime().getTime());
            }
        });
    }



    public void addExpenseAndMoveToMain(View v) {
        Intent i = new Intent(this, MainActivity.class);

        Spinner spinner = findViewById(R.id.categorySpinner);
        String category = spinner.getSelectedItem().toString();
        Expense exp = getExpenseFromControls();

        CalendarView calView = findViewById((R.id.calendarView));
        Date date = new Date(calView.getDate());

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        Expenses loadedExpense = Expenses.loadExpensesData(month, year, this);
        if(loadedExpense == null) {
            loadedExpense = new Expenses(month, year);
        }
        loadedExpense.add(exp.getAmount(), category, exp.getDate());
        boolean saved = Expenses.saveExpensesData(month, year, this, loadedExpense);
        if(saved) {
            startActivity(i);
        } else {
            // TODO : Toast Error Message
            Log.d("SAVE ERROR", "Couldn't save to file.");
        }
    }


    public Expense getExpenseFromControls() {
        TextView tv = findViewById(R.id.editAmount);
        float amount = Float.parseFloat(tv.getText().toString());
        CalendarView dp = findViewById((R.id.calendarView));

        Date date = new Date(dp.getDate());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if(checkControlsFilled()) {
            return new Expense(amount, date);
        } else {
            return null;
        }
    }

    public boolean checkControlsFilled() {
        TextView tv = findViewById(R.id.editAmount);
        CalendarView dp = findViewById((R.id.calendarView));
        Spinner spinner = findViewById(R.id.categorySpinner);

        return tv.getText().length() > 0 && spinner.getSelectedItem() != null;// && dp.getDate() != null;
    }
}