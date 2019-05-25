package com.example.mobile.Views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mobile.Views.Fragments.speaker.PollsFragment;
import com.example.mobile.Views.Fragments.speaker.QuestionsFragment;
import com.example.mobile.Views.Fragments.speaker.SurveysFragment;

public class ConfViewPagerAdapter extends FragmentPagerAdapter {


    public ConfViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                return new QuestionsFragment();
            case 1:
                return new SurveysFragment();
            case 2:
                return new PollsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int i){
        switch(i)
        {
            case 0:
                return "Questions";
            case 1:
                return "Surveys";
            case 2:
                return "Polls";
            default:
                return null;
        }
    }
}
