package com.example.wheaterub;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabs_count;
    Context context;
    public PagerAdapter(@NonNull FragmentManager fm, int tabs_count, Context context) {
        super(fm,tabs_count);
        this.tabs_count=tabs_count;
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TodayFragment today= new TodayFragment(context);
                return today;
            case 1:
                WeekFragment week= new WeekFragment(context);
                return week;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs_count;
    }
}
