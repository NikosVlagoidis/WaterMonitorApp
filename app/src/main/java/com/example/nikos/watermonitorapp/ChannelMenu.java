package com.example.nikos.watermonitorapp;

/**
 * Created by Nikos on 11/07/16.
 */
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class ChannelMenu extends AppCompatActivity {

    String[] projection;
    private SimpleCursorAdapter adapter;
    private ConnectionManager conmanager;
    private ArrayList IdDatabase = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_menu_fab);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.plus_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChannelMenu.this, AddChannelActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        conmanager = new ConnectionManager(this);
        ListView deviceView = (ListView) findViewById(R.id.AvailableDeviceslistView);
        projection = new String[]{
                ChannelDatabase.DbEntry._ID,
                ChannelDatabase.DbEntry.COLUNM_NAME_ID
        };
        Cursor cursor = conmanager.getDb().query(
                ChannelDatabase.DbEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        String[] fromColums = {ChannelDatabase.DbEntry.COLUNM_NAME_ID};
        int[] toViews = {R.id.deviceItem};

        adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, fromColums, toViews, 0);
        deviceView.setAdapter(adapter);
        deviceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChannelMenu.this, GraphActivity.class);
                intent.putExtra("key1", id);
                startActivityForResult(intent, 1);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {


                String result=data.getStringExtra("result");

                IdDatabase.add(result);


                ArrayAdapter<String> devAdapter = new ArrayAdapter<String>(ChannelMenu.this, R.layout.list_item, IdDatabase);
                ListView deviceView = (ListView) findViewById(R.id.AvailableDeviceslistView);
                deviceView.setAdapter(devAdapter);


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
}

