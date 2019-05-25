package com.example.mobile.Views.activities;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ViewInterfaces.ConferenceListView;
import com.example.mobile.Views.adapters.ConferenceListAdapter;
import com.example.mobile.presenters.ConferenceListPresenter;

import java.util.List;

public class ConferenceListActivity extends AppCompatActivity implements ConferenceListView {

    private RecyclerView activeConfList;
    private RecyclerView pastConfList;
    private FloatingActionButton createConfBtn;

    private ConferenceListPresenter presenter;

    private SharedPreferences prefs;

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
        presenter = new ConferenceListPresenter(this, repository);

        createConfBtn = findViewById(R.id.button_conflist_addconf);

        createConfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConferenceListActivity.this, CreateConferenceActivity.class));
            }
        });

        activeConfList = findViewById(R.id.recyclerview_active_conference_list);
        pastConfList = findViewById(R.id.recyclerview_past_conference_list);

        //final int uid = 1;

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        presenter.loadConfs(
                prefs.getInt("uid", 0),
                prefs.getString("token", "")
        );

    }

    @Override
    public void showConfs(List<Conference> activeConfs, List<Conference> pastConfs) {
        TextView activeListTitle = findViewById(R.id.textview_active_confs);
        TextView pastListTitle = findViewById(R.id.textview_past_confs);

        pastListTitle.setVisibility(View.VISIBLE);
        activeListTitle.setVisibility(View.VISIBLE);

        activeConfList.setAdapter(new ConferenceListAdapter(activeConfs, this));
        activeConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activeConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        pastConfList.setAdapter(new ConferenceListAdapter(pastConfs, this));
        pastConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pastConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    @SuppressLint("RestrictedApi")
    public void showEmptyListView(){
        ViewGroup emptyListLayout = findViewById(R.id.layout_conflist_emptylist);
        ViewGroup normalLayout = findViewById(R.id.layout_conflist_list);
        Button createConfBtn2 = findViewById(R.id.button_conflist_emptylist_addconf);

        emptyListLayout.setVisibility(View.VISIBLE);
        normalLayout.setVisibility(View.GONE);
        createConfBtn.setVisibility(View.GONE);

        createConfBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConferenceListActivity.this, CreateConferenceActivity.class));
            }
        });
    }

    @Override
    public void showConfs(List<Conference> confs, String type) {
        TextView pastListTitle = findViewById(R.id.textview_past_confs);
        TextView activeListTitle = findViewById(R.id.textview_active_confs);


        if(type.equals("past")){

            activeListTitle.setVisibility(View.GONE);
            pastListTitle.setVisibility(View.VISIBLE);


            pastConfList.setAdapter(new ConferenceListAdapter(confs, this));
            pastConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            pastConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }else{
            activeListTitle.setVisibility(View.VISIBLE);
            pastListTitle.setVisibility(View.GONE);

            activeConfList.setAdapter(new ConferenceListAdapter(confs, this));
            activeConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            activeConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }

    }

    @Override
    public void showErrorView() {
        ViewGroup errorView = findViewById(R.id.layout_conflist_error);
        ViewGroup listView = findViewById(R.id.layout_conflist_list);
        Button retryBtn = findViewById(R.id.button_conflist_tryagain);

        errorView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

                presenter.loadConfs(
                        prefs.getInt("uid", 0),
                        prefs.getString("token", "")
                );
            }
        });
    }


}
