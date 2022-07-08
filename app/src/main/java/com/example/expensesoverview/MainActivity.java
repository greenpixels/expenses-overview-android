package com.example.expensesoverview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.expensesoverview.helpers.ExpenseSlice;
import com.example.expensesoverview.views.PieChart;
import com.example.expensesoverview.views.PieChartLabel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout lc = (LinearLayout) findViewById(R.id.labelContainer);
        PieChart pc = (PieChart) findViewById(R.id.pieChart);

        for(ExpenseSlice slice : pc.getSlices()) {
            PieChartLabel pcl = new PieChartLabel(this);
            pcl.setText(slice.getCategory().name());
            pcl.setColour(slice.getColour());
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
}



