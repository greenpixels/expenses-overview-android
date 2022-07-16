package com.example.expensesoverview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        EditText yearControl = findViewById(R.id.editYear);
        Spinner monthControl = findViewById(R.id.spinnerMonth);

        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        yearControl.setText(Integer.toString(cal.get(Calendar.YEAR)));
        monthControl.setSelection(cal.get(Calendar.MONTH));
    }

    public void changeDateAndGoToMain(View v) {
        EditText yearControl = findViewById(R.id.editYear);
        Spinner monthControl = findViewById(R.id.spinnerMonth);

        int month = monthControl.getSelectedItemPosition();
        int year = Integer.parseInt(yearControl.getText().toString());

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("eMonth", month);
        i.putExtra("eYear", year);
        startActivity(i);
    }
}