package com.wolfie.waterreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class allrecord extends SQLiteOpenHelper {

    public allrecord(@Nullable Context context) {
        super(context,"MYDB1", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
        public void insert(int qty){
            SQLiteDatabase sd = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("qty",qty);
            sd.insert("allrecord",null,cv);


        }
    public String getDataavg()
    {SQLiteDatabase sd = this.getReadableDatabase();
        String get = "";
        Cursor c =sd.rawQuery("select avg(qty) from allrecord",null);
        c.moveToFirst();
       int avg = c.getInt(0);
        return String.valueOf((avg));
    }
    public String getDatamax()
    {SQLiteDatabase sd = this.getReadableDatabase();
    String get = "";
        Cursor c =sd.rawQuery("select max(qty) from allrecord where qty>0",null);
        c.moveToFirst();
        int avg = c.getInt(0);
        return String.valueOf((avg));
    }
    public String getDatamin()
    {SQLiteDatabase sd = this.getReadableDatabase();
        String get = "";
        Cursor c =sd.rawQuery("select min(qty) from allrecord where qty>0",null);
        c.moveToFirst();
        int avg = c.getInt(0);
        return String.valueOf((avg));
    }

}
