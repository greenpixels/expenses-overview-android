package com.example.expensesoverview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.example.expensesoverview.MainActivity;
import com.example.expensesoverview.R;
import com.example.expensesoverview.helpers.Category;
import com.example.expensesoverview.helpers.ExpenseSlice;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class PieChart extends View {

    private List<ExpenseSlice> slices = new ArrayList<ExpenseSlice>();
    public String[] Colours = {"#083D77", "#EBEBD3", "#DA4167", "#F4D35E", "#F78764", "#3D315B"};

    public PieChart(Context context) {
        super(context);
        init(null);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set) {
        this.slices.add(new ExpenseSlice(50.0F, Category.GROCERIES, Color.parseColor(Colours[slices.size()])));
        this.slices.add(new ExpenseSlice(10.0F, Category.CAR_FUEL, Color.parseColor(Colours[slices.size()])));
        this.slices.add(new ExpenseSlice(10.0F, Category.GROCERIES, Color.parseColor(Colours[slices.size()])));
        this.slices.add(new ExpenseSlice(10.0F, Category.GROCERIES, Color.parseColor(Colours[slices.size()])));
        this.slices.add(new ExpenseSlice(10.0F, Category.GROCERIES, Color.parseColor(Colours[slices.size()])));
        this.slices.add(new ExpenseSlice(10.0F, Category.GROCERIES, Color.parseColor(Colours[slices.size()])));
    }

    public void setSlices(List<ExpenseSlice> slices) {
        this.slices = slices;
    }

    public List<ExpenseSlice> getSlices() {
        return this.slices;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int colourIndex = 0;
        float currentAngle = 0.0F;

        for(ExpenseSlice slice : slices) {

            // Get Colour
            Paint p = new Paint();
            p.setColor(Color.parseColor(Colours[colourIndex]));
            if(colourIndex < Colours.length - 1) {
                colourIndex++;
            } else {
                colourIndex = 0;
            }

            // Draw Slice
            float sliceAmount = (slice.getPercentage() / 100.0F) * 360.0F;
            int size = Math.min(this.getWidth(), this.getHeight());
            canvas.drawArc(0, 0, size, size, currentAngle, sliceAmount, true, p);
            currentAngle += sliceAmount;

            // Inner Circle
            Paint innerColour = new Paint();
            innerColour.setColor(Color.parseColor("#FFFFFF"));
            canvas.drawCircle(size/2, size/2, size/4 , innerColour);
        }
    }
}
