package com.example.mobile.Views.adapters.speaker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mobile.Views.Fragments.speaker.QuestionsFragment;
import com.example.mobile.Views.Fragments.speaker.SurveysFragment;

public class viewPagerAdapter extends FragmentPagerAdapter {


    public viewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new QuestionsFragment();
            case 1:
                return new SurveysFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return "Questions";
            case 1:
                return "Surveys";
            default:
                return null;
        }

    }
}
