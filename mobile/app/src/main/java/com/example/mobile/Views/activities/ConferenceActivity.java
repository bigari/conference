package com.example.mobile.Views.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mobile.R;
import com.example.mobile.Views.ViewInterfaces.ConferenceView;


public class ConferenceActivity extends AppCompatActivity implements ConferenceView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_tablayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);
        String title = getIntent().getExtras().getString("confTitle", "Event");
        String accessCode = getIntent().getExtras().getString("accessCode", "");
        toolbar.setTitle(title);
        toolbar.setSubtitle("#" + accessCode);

        ViewPager viewPager = findViewById(R.id.viewpager_conferenceinfo);

        String role = getIntent().getExtras().getString("role");

        if(role.equals("speaker")){
            viewPager.setAdapter(new com.example.mobile.Views.adapters.speaker.viewPagerAdapter(getSupportFragmentManager()));
        }
        else{
            viewPager.setAdapter(new com.example.mobile.Views.adapters.participant.viewPagerAdapter(getSupportFragmentManager()));
        }

        TabLayout tabLayout = findViewById(R.id.tablayout_conferenceinfo);
        tabLayout.setupWithViewPager(viewPager);
    }




}
