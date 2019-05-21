package com.example.mobile.Views.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ConferenceListView;
import com.example.mobile.Views.adapters.ConferenceListAdapter;
import com.example.mobile.presenters.ConferenceListPresenter;

import java.util.List;

public class ConferenceListActivity extends AppCompatActivity implements ConferenceListView {

    private RecyclerView activeConfList;
    private RecyclerView pastConfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_conference_list);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        ConferenceRepository repository = new ConferenceRepository();
        ConferenceListPresenter presenter = new ConferenceListPresenter(this, repository);

        FloatingActionButton createConfBtn = findViewById(R.id.button_conf_list_add_btn);

        createConfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConferenceListActivity.this, CreateConferenceActivity.class));
            }
        });

        activeConfList = findViewById(R.id.recyclerview_active_conference_list);
        pastConfList = findViewById(R.id.recyclerview_past_conference_list);

        final int uid = 1;
        presenter.loadConfs(uid);

    }

    @Override
    public void showConfs(List<Conference> activeConfs, List<Conference> pastConfs) {

        activeConfList.setAdapter(new ConferenceListAdapter(activeConfs, this));
        activeConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activeConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        pastConfList.setAdapter(new ConferenceListAdapter(pastConfs, this));
        pastConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pastConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

}
