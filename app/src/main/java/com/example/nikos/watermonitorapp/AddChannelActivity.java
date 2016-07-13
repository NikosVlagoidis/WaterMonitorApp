package com.example.nikos.watermonitorapp;

import android.app.Activity;
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
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_channel);
        final EditText ChannelId = (EditText)findViewById(R.id.ChannelId);
        final EditText ChannelNickname = (EditText)findViewById(R.id.ChannelNickName);
        final Button GoButton = (Button)findViewById(R.id.GoBtn);
        GoButton.setEnabled(false);

        ChannelId.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable arg0) {
                if (ChannelId.length() == 0 || ChannelNickname.length() == 0){
                    GoButton.setEnabled(false);
                    GoButton.setClickable(false);
                }
                else{
                    GoButton.setEnabled(true);
                    GoButton.setClickable(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        ChannelNickname.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable arg0) {
                if (ChannelId.length() == 0 || ChannelNickname.length() == 0){
                    GoButton.setEnabled(false);
                    GoButton.setClickable(false);
                }
                else{
                    GoButton.setEnabled(true);
                    GoButton.setClickable(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        GoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = ChannelId.getText().toString();
                String k = ChannelNickname.getText().toString();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",s);
                returnIntent.putExtra("Nickname",k);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}

