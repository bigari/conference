package com.example.mobile.Views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Views.ConferenceListView;
import com.example.mobile.presenters.ConferenceListPresenter;

public class ConferenceListActivity extends AppCompatActivity implements ConferenceListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_list2);

        ConferenceRepository repository = new ConferenceRepository();
        ConferenceListPresenter presenter = new ConferenceListPresenter(this, repository);



    }

    @Override
    public void showConfs() {

    }
}
