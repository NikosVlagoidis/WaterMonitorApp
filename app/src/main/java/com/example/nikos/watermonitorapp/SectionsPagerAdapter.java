package com.example.nikos.watermonitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private int id;

    public SectionsPagerAdapter(FragmentManager fm,int kk) {
        super(fm);
        id = kk;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a LineChartFragment (defined as a static inner class below).
        Fragment frag;
        Bundle args1 = new Bundle();
        args1.putInt("cid", id);
        if (position == 0) {
            frag = LineChartFragment.newInstance(position + 1);
        } else {
            frag = ColumnChartFragment.newInstance(position + 1);
        }
        frag.setArguments(args1);
        return frag;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
        }
        return null;
    }
}
