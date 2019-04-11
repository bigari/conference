package com.example.mobile.Views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mobile.R;
import com.example.mobile.Views.ConferenceView;
import com.example.mobile.Repositories.models.Conference;

public class ConferenceActivity extends AppCompatActivity implements ConferenceView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);
    }

    @Override
    public void showConference(Conference conference) {

    }

    @Override
    public void showError() {

    }
}
