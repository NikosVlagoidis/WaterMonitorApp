package com.example.nikos.watermonitorapp;

/**
 * Created by Nikos on 11/07/16.
 */
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.macroyau.thingspeakandroid.ThingSpeakChannel;


public class ChannelMenu extends AppCompatActivity {

    String[] projection;
    private SimpleCursorAdapter adapter;
    private ConnectionManager conmanager;
    private MyDbHelper dbHelper;
    private ThingSpeakChannel tsChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_menu_fab);
        tsChannel = new ThingSpeakChannel(132764);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.plus_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChannelMenu.this, AddChannelActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        conmanager = new ConnectionManager(this);
        final ListView deviceView = (ListView) findViewById(R.id.AvailableDeviceslistView);
        projection = new String[]{
                ChannelDatabase.DbEntry._ID,
                ChannelDatabase.DbEntry.COLUNM_NAME_ID,
                ChannelDatabase.DbEntry.COLUMN_NAME_NICKNAME
        };
        Cursor cursor = conmanager.getDb().query(
                ChannelDatabase.DbEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        String[] fromColums = {ChannelDatabase.DbEntry.COLUMN_NAME_NICKNAME};
        int[] toViews = {R.id.deviceItem};

        adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, fromColums, toViews, 0);
        deviceView.setAdapter(adapter);
        deviceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int item = ((Cursor) deviceView.getItemAtPosition(position)).getInt(1);


                Intent i = new Intent(ChannelMenu.this, GraphActivity.class);
                i.putExtra("kati",item);
                startActivity(i);
            }

        });
        deviceView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(ChannelMenu.this, "Channel Deleted", Toast.LENGTH_SHORT).show();
                int k = ((Cursor) deviceView.getItemAtPosition(arg2)).getInt(0);
                deleteEntry(k);
                Cursor cursor = conmanager.getDb().query(
                        ChannelDatabase.DbEntry.TABLE_NAME,  // The table to query
                        projection,                               // The columns to return
                        null,                                // The columns for the WHERE clause
                        null,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        null                                        // The sort order
                );
                adapter.changeCursor(cursor);
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result=data.getStringExtra("result");
                String nickname=data.getStringExtra("Nickname");
                addEntry(nickname, Integer.parseInt(result));
                Cursor cursor = conmanager.getDb().query(
                        ChannelDatabase.DbEntry.TABLE_NAME,  // The table to query
                        projection,                               // The columns to return
                        null,                                // The columns for the WHERE clause
                        null,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        null                                        // The sort order
                );
                adapter.changeCursor(cursor);
            }
        }
    }
    public void addEntry(String nickname, int id){
        // Gets the data repository in write mode
        SQLiteDatabase db = conmanager.getDb();
        if(!inDb(db,id)){
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(ChannelDatabase.DbEntry.COLUMN_NAME_NICKNAME, nickname);
            values.put(ChannelDatabase.DbEntry.COLUNM_NAME_ID, id);
            // Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = db.insert(
                    ChannelDatabase.DbEntry.TABLE_NAME,
                    null,
                    values);}
    }
    public void deleteEntry(int id){
        SQLiteDatabase db = conmanager.getDb();
        db.delete(ChannelDatabase.DbEntry.TABLE_NAME,"_ID = ?",new String[]{""+ id});
    }
    public  boolean inDb(SQLiteDatabase sqldb,
                                int id) {
        String[] projection = new String[]{
                ChannelDatabase.DbEntry.COLUNM_NAME_ID
        };
        Cursor cursor = sqldb.query(
                ChannelDatabase.DbEntry.TABLE_NAME,
                projection,
                ChannelDatabase.DbEntry.COLUNM_NAME_ID + " = ?",
                new String[]{Integer.toString(id)},
                null, null, null, null
        );
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}

