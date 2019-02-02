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
                return new GoogleMapActivity();
            case 1:
                return new ChatActivity();
            case 2:
                return new DroneActivity();
            default:
                return new GoogleMapActivity();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
