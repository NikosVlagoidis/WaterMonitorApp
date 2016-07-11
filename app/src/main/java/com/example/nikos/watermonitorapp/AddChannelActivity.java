package com.example.nikos.watermonitorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddChannelActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_channel);
        final EditText ChannelId = (EditText)findViewById(R.id.ChannelId);
        final Button GoButton = (Button)findViewById(R.id.GoBtn);
        GoButton.setEnabled(false);

        ChannelId.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ChannelId.length() == 0){
                    GoButton.setEnabled(false);
                    GoButton.setClickable(false);
                }else{
                    GoButton.setEnabled(true);
                    GoButton.setClickable(true);
                }
            }
        });

        GoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = ChannelId.getText().toString();
                Bundle forChannelMenu= new Bundle();
                forChannelMenu.putString("ChannelId", s);
                Intent a=new Intent(AddChannelActivity.this,MainActivity.class);
                a.putExtras(forChannelMenu);
                startActivity(a);

            }
        });




    }
}

