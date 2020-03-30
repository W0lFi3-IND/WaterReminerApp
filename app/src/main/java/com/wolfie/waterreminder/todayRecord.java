package com.wolfie.waterreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class todayRecord extends SQLiteOpenHelper {

    public todayRecord(@Nullable Context context) {
        super(context,"MYDB1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todayrecord (sno integer primary key autoincrement, qty integer,Time REAL DEFAULT (datetime('now', 'localtime')))");
        db.execSQL("create table allrecord (sno integer primary key autoincrement, qty integer,Time DATETIME DEFAULT CURRENT_DATE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(){
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("qty",200);
        sd.insert("todayrecord",null,cv);


    }
    public Cursor getData()
    {SQLiteDatabase sd = this.getReadableDatabase();
        Cursor c =sd.rawQuery("select * from todayrecord",null);
        return c;

    }

    public void deleteAll()
    {
        SQLiteDatabase sd = this.getWritableDatabase();
        sd.execSQL("delete from todayrecord");
        sd.close();
    }
}
