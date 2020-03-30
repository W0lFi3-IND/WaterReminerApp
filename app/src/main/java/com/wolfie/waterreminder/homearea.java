package com.wolfie.waterreminder;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import me.itangqi.waveloadingview.WaveLoadingView;


/**
 * A simple {@link Fragment} subclass.
 */
public class homearea extends Fragment {
    float VOL;
ImageButton btn;
TextView tv;
     Float totalwater;
WaveLoadingView mWaveLoadingView;
SharedPreferences addwater;
RecyclerView rv;
todayRecord md;
String sno;
String volume;
String Date;
    todayadapter td;
Cursor c;
ArrayList<todayrecordgetter> al = new ArrayList<>();
    public homearea() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_homearea, container, false);
        addwater = this.getActivity().getSharedPreferences("addwater",0);
        rv = view.findViewById(R.id.todayRecord);
        btn = view.findViewById(R.id.imageButton3);
        tv = view.findViewById(R.id.Left);
        // Get the total volume
        SharedPreferences pref = this.getActivity().getSharedPreferences("WaterCap",0);
  totalwater = pref.getFloat("totalwater",0);
        mWaveLoadingView = (WaveLoadingView) view.findViewById(R.id.waveLoadingView);
       pro();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(layoutManager);
        td = new todayadapter(this.getActivity(),al);
        float vol = addwater.getFloat("waterQty",0);
        float perc = (vol/totalwater)*100;
        mWaveLoadingView.setProgressValue((int) perc);
rv.setAdapter(td);
       final SharedPreferences.Editor editor = addwater.edit();
       //
        md = new todayRecord(this.getActivity());
        // change the progress on click of a button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float vol = addwater.getFloat("waterQty",0);
                editor.putFloat("waterQty",vol+200);
                editor.apply();
                float perc = (vol/totalwater)*100;
                mWaveLoadingView.setProgressValue((int) perc);
                tv.setText(""+vol+"/"+totalwater);
                editor.commit();
                md.insert();
                al.clear();
                d();
                td.notifyDataSetChanged();
                pro();
                startAlert();

            }
        });
        al.clear();
        d();
        td.notifyDataSetChanged();
        // Inflate the layout for this fragment
        return view;
    }
    void pro() {

        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        mWaveLoadingView.setBorderWidth(10);
        VOL = addwater.getFloat("waterQty",0);
        int a = (int)(VOL);
        float perc = (a/totalwater)*100;
        tv.setText(""+VOL+"/"+totalwater);
        mWaveLoadingView.setProgressValue((int)perc);
        mWaveLoadingView.setAmplitudeRatio(60);
        mWaveLoadingView.setTopTitleStrokeWidth(3);
        mWaveLoadingView.setAnimDuration(3000);
    }
    public void startAlert(){
        Intent intent = new Intent(this.getActivity(),broadcasthome.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getActivity(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND,00);
        long startUpTime = calendar.getTimeInMillis();
        
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, startUpTime,AlarmManager.INTERVAL_DAY , pendingIntent);
    }

    public void d(){
        c = md.getData();
        if (c.getCount() > 0) {
            if (c.moveToNext()) {
                do {
                    sno = c.getString(0);
                    volume = c.getString(1);
                    Date = c.getString(2);
                    todayrecordgetter trd = new todayrecordgetter(sno, volume, Date);
                    al.add(trd);
                } while (c.moveToNext());
            }
        }

    }

}
