package com.example.expensesoverview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.expensesoverview.R;

public class PieChartLabel extends View {
    private String text = "Label Text";
    private int colour = Color.parseColor("#FFF333");
    public int index;

    public PieChartLabel(Context context) {
        super(context);
        this.init(null);
    }

    public PieChartLabel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init(attrs);
    }

    public PieChartLabel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(attrs);
    }

    public PieChartLabel(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init(attrs);
    }

    public void init(@Nullable AttributeSet attrs) {
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint rectPaint = new Paint();
        rectPaint.setColor(colour);
        canvas.drawRoundRect(0, 0, this.getHeight(), this.getHeight(), getHeight()*0.1F, getHeight()*0.1F, rectPaint);

        Paint textPaint = new Paint();
        textPaint.setTextSize(this.getHeight());
        textPaint.setColor(getResources().getColor(R.color.text_colour));
        canvas.drawText(text, this.getHeight()*1.5F, this.getHeight()*0.85F, textPaint);
    }
}
