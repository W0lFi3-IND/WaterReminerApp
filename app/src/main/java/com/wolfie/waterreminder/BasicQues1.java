package com.wolfie.waterreminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.kevalpatel2106.rulerpicker.RulerValuePicker;
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener;

public class BasicQues1 extends AppCompatActivity {
NumberPicker np;
ImageView iv;
Animation mAnimation;
ImageButton mButton;
int age;
float weight;
    RulerValuePicker rp;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_ques1);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("BasicInfo", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();
        np = findViewById(R.id.agepick);
        iv =findViewById(R.id.imageView);
        np.setMaxValue(100);
        np.setMinValue(1);
        np.setWrapSelectorWheel(true);
        findViewById(R.id.next1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("Age",age);
                editor.putFloat("Weight",weight);
                editor.commit();
                Intent i = new Intent(getApplicationContext(),cal.class);
                startActivity(i);
            }
        });
        mAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                age=newVal;
                if(newVal>=1 && newVal <=10)
                { iv.setImageResource(R.drawable.baby);
                iv.startAnimation(mAnimation);
                }
                else if(newVal>=11 && newVal <=25)
                {iv.setImageResource(R.drawable.teen);
                    iv.startAnimation(mAnimation);
                }
                else if (newVal>=26 && newVal <=50)
                { iv.setImageResource(R.drawable.adult);
                    iv.startAnimation(mAnimation);
                }
                else if(newVal>=51 && newVal <=100)
                { iv.setImageResource(R.drawable.old);
                    iv.startAnimation(mAnimation);
                }
            }
        });
        rp = findViewById(R.id.ruler_picker);
        tv = findViewById(R.id.tv);
        rp.setValuePickerListener(new RulerValuePickerListener() {
            @Override
            public void onValueChange(int selectedValue) {
                weight = selectedValue;
                tv.setText(""+selectedValue+" Kg");
            }

            @Override
            public void onIntermediateValueChange(int selectedValue) {

            }
        });

    }
}
