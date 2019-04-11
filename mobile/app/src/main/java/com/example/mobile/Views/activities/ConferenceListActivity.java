package com.example.mobile.Views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Views.ConferenceListView;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.presenters.ConferenceListPresenter;
import java.util.List;


public class ConferenceListActivity extends AppCompatActivity implements ConferenceListView {

    public static final String TAG = "ConferenceListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_list);


        ConferenceRepository conferenceRepository = new ConferenceRepository();
        ConferenceListPresenter presenter = new ConferenceListPresenter(this, conferenceRepository);

        presenter.loadConferenceList();

    }

    @Override
    public void showConferences(List<Conference> conferences) {
        RecyclerView recyclerView = findViewById(R.id.recyclerview_conference_list);
        recyclerView.setAdapter(new ConferenceListAdapter(this, conferences));

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showError() {
        Toast.makeText(this, "An Error occurred while loading the conference List.", Toast.LENGTH_SHORT).show();
    }
}
