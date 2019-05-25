package com.example.mobile.Views.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mobile.R;
import com.example.mobile.Views.ViewInterfaces.ConferenceView;
import com.example.mobile.Views.adapters.ConfViewPagerAdapter;

public class ConferenceActivity extends AppCompatActivity implements ConferenceView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_info);

        ViewPager viewPager = findViewById(R.id.viewpager_conferenceinfo);

        viewPager.setAdapter(new ConfViewPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tablayout_conferenceinfo);
        tabLayout.setupWithViewPager(viewPager);

    }


}
