package com.example.mobile.Views.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    private ProgressBar progressBar;
    private LinearLayout emptyListLayout;
    private RelativeLayout confListLayout;

    private ConferenceListPresenter presenter;
    private String token;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_conference_list);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        progressBar = findViewById(R.id.progressbar);
        emptyListLayout = findViewById(R.id.layout_conflist_emptylist);
        confListLayout = findViewById(R.id.layout_conflist_list);

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

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        this.token = prefs.getString("token", "");
        this.uid = Integer.parseInt(prefs.getString("uid", "0"));

        presenter.loadConfs(uid, token);
    }

    @Override
    public void showConfs(List<Conference> activeConfs, List<Conference> pastConfs) {
        TextView activeListTitle = findViewById(R.id.textview_active_confs);
        TextView pastListTitle = findViewById(R.id.textview_past_confs);

        confListLayout.setVisibility(View.VISIBLE);
        pastListTitle.setVisibility(View.VISIBLE);
        activeListTitle.setVisibility(View.VISIBLE);

        activeConfList.setAdapter(new ConferenceListAdapter(activeConfs, this, presenter));
        activeConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activeConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        pastConfList.setAdapter(new ConferenceListAdapter(pastConfs, this, presenter));
        pastConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pastConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    @SuppressLint("RestrictedApi")
    public void showEmptyListView(){
        Button createConfBtn2 = findViewById(R.id.button_conflist_emptylist_addconf);

        emptyListLayout.setVisibility(View.VISIBLE);
        confListLayout.setVisibility(View.GONE);

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

        confListLayout.setVisibility(View.VISIBLE);

        if(type.equals("past")){

            activeListTitle.setVisibility(View.GONE);
            pastListTitle.setVisibility(View.VISIBLE);

            pastConfList.setAdapter(new ConferenceListAdapter(confs, this, presenter));
            pastConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            pastConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }else{
            activeListTitle.setVisibility(View.VISIBLE);
            pastListTitle.setVisibility(View.GONE);

            activeConfList.setAdapter(new ConferenceListAdapter(confs, this, presenter));
            activeConfList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            activeConfList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }

    }

    @Override
    public void showErrorView() {
        ViewGroup errorView = findViewById(R.id.layout_conflist_error);
        Button retryBtn = findViewById(R.id.button_conflist_tryagain);

        errorView.setVisibility(View.VISIBLE);

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorView.setVisibility(View.GONE);

                presenter.loadConfs(uid, token);
            }
        });
    }

    @Override public void showLoading() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideLoading() {
        this.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_deleteconf_title));
        builder.setMessage(getString(R.string.dialog_deleteconf_message));

        String positiveText = getString(R.string.dialog_deleteconf_delete);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.confirm(token);
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog dialog = builder.create();

        dialog.show();
    }


}
