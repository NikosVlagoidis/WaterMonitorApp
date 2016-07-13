package com.example.nikos.watermonitorapp;

import android.provider.BaseColumns;

public class ChannelDatabase {
    public ChannelDatabase(){}

    public static abstract class DbEntry implements BaseColumns {
        public static final String TABLE_NAME = "Channel";
        public static final String COLUNM_NAME_ID = "ID";
        public static final String COLUMN_NAME_NICKNAME = "Nickname";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DbEntry.TABLE_NAME + " (" +
                    DbEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    DbEntry.COLUNM_NAME_ID + " INTEGER" + COMMA_SEP +
                    DbEntry.COLUMN_NAME_NICKNAME + TEXT_TYPE + " )";

    public static String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + DbEntry.TABLE_NAME;
}
