package com.wolfie.waterreminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = getSharedPreferences("BasicInfo",0);
        int age = pref.getInt("Age",0);
        if(age!=0)
        {
            Intent i = new Intent(getApplicationContext(),home.class);
            startActivity(i);
            finish();

        }
     btn = findViewById(R.id.bt);
     btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),BasicQues1.class);
        startActivity(i);
    }
});

    }

}
