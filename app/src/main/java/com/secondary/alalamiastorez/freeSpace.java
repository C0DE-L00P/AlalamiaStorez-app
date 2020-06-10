package com.secondary.alalamiastorez;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class freeSpace extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_dialog);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#f6a672");
        colors.add("#1263a1");
        colors.add("#2a2a2a");

        LinearLayout colorsll = findViewById(R.id.colors_ll);

        int i=0;
        for (String color: colors){
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);
            lp.setMargins(0,0,5,0);
            iv.setLayoutParams(lp);
            iv.setId(i);
            iv.setBackground(drawCircle(Color.parseColor(color)));
            colorsll.addView(iv);
            i++;
        }
    }

    public static GradientDrawable drawCircle(int backgroundColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, 0, 0});
        shape.setColor(backgroundColor);
//        shape.setStroke(4, borderColor);
        return shape;
    }
}
