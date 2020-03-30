package com.wolfie.waterreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class broadcasthome extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        todayRecord td = new todayRecord(context);
        allrecord ad = new allrecord(context);
        SharedPreferences pref = context.getSharedPreferences("addwater",0);
        int a = (int) pref.getFloat("waterQty",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat("waterQty",0);
        editor.apply();
        td.deleteAll();
        ad.insert(a);
    }
}
