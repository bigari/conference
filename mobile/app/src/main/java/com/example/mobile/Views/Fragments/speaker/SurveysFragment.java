package com.example.mobile.Views.Fragments.speaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobile.R;
import com.example.mobile.Repositories.SurveyRepository;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Views.ViewInterfaces.speaker.SpeakerSurveyView;
import com.example.mobile.Views.activities.ManageSurveyActivity;
import com.example.mobile.Views.adapters.speaker.SpeakerSurveyAdapter;
import com.example.mobile.presenters.speaker.SpeakerSurveyPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SurveysFragment extends Fragment implements SpeakerSurveyView {

    private FloatingActionButton btnAddSurvey;
    private RecyclerView surveyRecycleView;
    private SpeakerSurveyPresenter presenter;
    private List<Enquete> surveys = new ArrayList<>();
    private SwipeRefreshLayout srl;
    private final ScheduledExecutorService newSurveyScheduler =
            Executors.newSingleThreadScheduledExecutor();

    private Context ctx;
    private Integer confId;
    private SpeakerSurveyAdapter surveyAdapter;

    private static final int PERIOD = 15000;
    Handler mHandler;
    Runnable mRunnable;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
            this.ctx = a;
            this.confId = a.getIntent().getExtras().getInt("confId");
        }

        mHandler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView =  inflater.inflate(R.layout.fragment_surveys_speaker, container, false);

        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        surveyRecycleView = view.findViewById(R.id.recyclerview_survey_speaker);
        surveyRecycleView.setLayoutManager(new LinearLayoutManager(ctx));
        btnAddSurvey = view.findViewById(R.id.button_surveylist_addsurvey);



        btnAddSurvey.setOnClickListener(v->{
            Intent intent = new Intent(ctx, ManageSurveyActivity.class);
            intent.putExtra("confId", confId);
            intent.putExtra("type", "create");
            startActivity(intent);
        });



        presenter = new SpeakerSurveyPresenter(this, new SurveyRepository());
        surveyAdapter = new SpeakerSurveyAdapter(ctx, this.surveys, presenter);
        presenter.loadSurveys(confId);

        mRunnable = new Runnable() {
            public void run() {
                presenter.getStats(confId);
                mHandler.postDelayed(this, PERIOD);
            }
        };
    }

    private void periodicFetchSurveyStats() {
        mHandler.post(mRunnable);
    }

    private void stopFetchingSurveyStats() {
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void showList(List<Enquete> surveys) {
        this.surveys = surveys;
        surveyAdapter = new SpeakerSurveyAdapter(ctx, this.surveys, presenter);
        surveyRecycleView.setAdapter(surveyAdapter);
        mHandler.post(mRunnable);
    }

    @Override
    public void showEmptyListView() {

    }


    @Override
    public void showErrorView() {

    }

    @Override
    public void stopRefreshingView() {

    }

    @Override
    public void updateStats(List<Enquete> enquetes) {
        int i=0;
        Enquete.updateStats(surveys, enquetes);
        surveyRecycleView.setAdapter(surveyAdapter);
        surveyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopFetchingSurveyStats();
    }

    @Override
    public void onResume() {
        super.onResume();
        periodicFetchSurveyStats();
        presenter.loadSurveys(confId);
    }

    @Override
    public void onStop() {
        super.onStop();
        stopFetchingSurveyStats();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopFetchingSurveyStats();
    }
}
