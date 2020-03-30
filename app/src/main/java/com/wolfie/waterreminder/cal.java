package com.wolfie.waterreminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.skyfishjy.library.RippleBackground;

import java.text.DecimalFormat;
public class cal extends AppCompatActivity {
TextView mTextView;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        SharedPreferences pref = getSharedPreferences("BasicInfo",0);
        int age = pref.getInt("Age",0);
        float weight = pref.getFloat("Weight",0);
        mTextView = findViewById(R.id.totalcount);
            float t1 = (float) (weight/30);
        SharedPreferences pref1 = getApplicationContext().getSharedPreferences("WaterCap", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref1.edit();
        editor.putFloat("totalwater",t1*1000);
        editor.commit();
        mTextView.setText(""+df2.format(t1)+" Lt");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), home.class);
                startActivity(i);
                finish(); }
        }, 5000);
        final SharedPreferences addwater =getApplicationContext().getSharedPreferences("AddWater",0);
        final SharedPreferences.Editor editor1 = addwater.edit();
        editor1.putFloat("waterQty", 200);
        editor1.commit();
        }

    }
