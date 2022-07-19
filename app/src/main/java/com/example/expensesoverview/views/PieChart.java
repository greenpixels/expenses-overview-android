package com.example.expensesoverview.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.expensesoverview.R;
import com.example.expensesoverview.helpers.ExpenseSlice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PieChart extends View {

    private List<ExpenseSlice> slices = new ArrayList<ExpenseSlice>();
    public String[] Colours = {"#083D77", "#EBEBD3", "#DA4167", "#F4D35E", "#F78764", "#3D315B", "#4EF5C7", "#C4F555", "#9185F0"};
    private int selectedSlice = -1;

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
        Resources res = getResources();
        String[] categories = res.getStringArray(R.array.string_array_categories);
        for(String category : categories) {
            this.slices.add(new ExpenseSlice(0.0F, category, Color.parseColor(Colours[slices.size()])));
        }
    }

    public void updateSlices(float[] values) {

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
        float size = Math.min(this.getWidth(), this.getHeight());

        // Background Circle
        boolean isEmpty = false;
        for(ExpenseSlice slice : slices) {
            if (slice.getPercentage() > 0) {
                isEmpty = true;
                break;
            }
        }

        if(!isEmpty) {
            Paint  backgroundColour = new Paint();
            backgroundColour.setColor(Color.parseColor("#A0B8C5"));
            canvas.drawCircle(size/2, size/2, size/2, backgroundColour);
        }


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

            if(slice.isSelected()) {
                canvas.drawArc(0, 0, size, size, currentAngle, sliceAmount, true, p);
            } else if(sliceAmount > 0){
                canvas.drawArc(size*0.02F, size*0.02F, size * 0.98F, size * 0.98F, currentAngle, sliceAmount, true, p);
            }
            currentAngle += sliceAmount;
        }

        // Inner Circle
        Paint innerColour = new Paint();
        innerColour.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawCircle(size/2, size/2, size/4 , innerColour);
    }

    public int getSelectedSlice() {
        return selectedSlice;
    }

    public void setSelectedSlice(int selectedSlice) {
        this.selectedSlice = selectedSlice;
    }
}
