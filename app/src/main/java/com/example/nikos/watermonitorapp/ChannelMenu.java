package com.example.nikos.watermonitorapp;

/**
 * Created by Nikos on 11/07/16.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChannelMenu extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_menu_layout);
        updateList();
    }

    /*
    Method to get sensors from Cloud and the adapter will adapt it.
     */
    private String[] getChannelFromManager(){
        /*Create a ConnectionManager Object and get channel method.*/
        //ConnectionManager cnm = new ConnectionManager();

        /*This String[] is for a quick testing*/
        String devices[] ={"Kitchen", "Bathroom", "Garden"};
        return devices;
    }


    /*
    Method to update Names of Available Sensors. The Adapter will adapt them to User Interface.
     */
    private void updateList() {
        String data[] = getChannelFromManager();

        ArrayAdapter<String> devAdapter = new ArrayAdapter<String>(ChannelMenu.this, R.layout.list_item, data);
        ListView deviceView = (ListView) findViewById(R.id.AvailableDeviceslistView);
        deviceView.setAdapter(devAdapter);

        deviceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


}
