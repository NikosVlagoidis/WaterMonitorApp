package com.example.nikos.watermonitorapp;

import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class GraphActivity extends AppCompatActivity {

        private SectionsPagerAdapter mSectionsPagerAdapter;

        /**
         * The {@link ViewPager} that will host the section contents.
         */
        private ViewPager mViewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            setTheme(R.style.GraphTheme);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_graph);

            Intent i = getIntent();
            Integer kk= i.getExtras().getInt("kati");
            Log.d("LOGGGG",kk.toString() );

            // Show Back Button and handle its events.
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
        }

        // Handles the Back Button pressing event.
        @Override
        public void onBackPressed() {
            super.onBackPressed();
        }
}
