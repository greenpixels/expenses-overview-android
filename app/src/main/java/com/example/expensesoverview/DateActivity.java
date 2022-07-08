package com.example.expensesoverview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DateActivity extends AppCompatActivity {

    public String[] months = {"January", "February"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
    }
}