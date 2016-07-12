package com.example.nikos.watermonitorapp;

import android.provider.BaseColumns;

/**
 * Created by Nikos on 12/07/16.
 */
public class ChannelDatabase {
    public ChannelDatabase(){

    }
    public static abstract class DbEntry implements BaseColumns {
        public static final String TABLE_NAME = "Channel";
        public static final String COLUNM_NAME_ID = "ID";
    }
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DbEntry.TABLE_NAME + " (" +
                        DbEntry._ID + " INTEGER PRIMARY KEY," +
                        DbEntry.COLUNM_NAME_ID + " INTEGER" + " )";


    public static String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + DbEntry.TABLE_NAME;
}
