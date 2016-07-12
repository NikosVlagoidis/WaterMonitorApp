package com.example.nikos.watermonitorapp;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Nikos on 12/07/16.
 */
public class ConnectionManager {
    private MyDbHelper dbHelper;
    private SQLiteDatabase db;

    public ConnectionManager(ChannelMenu main) {
        MyDbHelper dbHelper = new MyDbHelper(main);
        db = dbHelper.getReadableDatabase();
    }
    public SQLiteDatabase getDb(){
        return db;
    }


}
