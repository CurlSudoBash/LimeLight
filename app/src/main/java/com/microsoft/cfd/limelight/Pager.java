package com.microsoft.cfd.limelight;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                GoogleMapActivity googleMapActivity = new GoogleMapActivity();
                return googleMapActivity;
            case 1:
                ChatActivity chatActivity = new ChatActivity();
                return chatActivity;
            case 2:
                DroneActivity droneActivity = new DroneActivity();
                return droneActivity;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
