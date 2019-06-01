package com.example.mobile.Views.Fragments.participant;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.mobile.Repositories.models.Option;
import com.example.mobile.Views.ViewInterfaces.participant.ParticipantSurveyView;
import com.example.mobile.Views.adapters.participant.ParticipantSurveyAdapter;
import com.example.mobile.presenters.participant.SurveyPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SurveysFragment extends Fragment implements ParticipantSurveyView {

    private RecyclerView surveyRecycleView;
    private SurveyPresenter presenter;
    private List<Enquete> surveys = new ArrayList<>();
    private SwipeRefreshLayout srl;
    private final ScheduledExecutorService newSurveyScheduler =
            Executors.newSingleThreadScheduledExecutor();

    private Context ctx;
    private Integer confId;
    private ParticipantSurveyAdapter surveyAdapter;

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
        View rootView =  inflater.inflate(R.layout.fragment_surveys_participant, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        surveyRecycleView = view.findViewById(R.id.recyclerview_surveys_participant);
        surveyRecycleView.setLayoutManager(new LinearLayoutManager(ctx));
        //srl = view.findViewById(R.id.swipecontainer_surveylist);


        /*srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.fetchNew(confId);
            }
        });*/

        presenter = new SurveyPresenter(this, new SurveyRepository());
        surveyAdapter = new ParticipantSurveyAdapter(ctx, this.surveys, presenter);
        presenter.loadSurveys(confId);

        mRunnable = new Runnable() {
            public void run() {
                presenter.fetchNew(confId);
                presenter.getStats(confId);
                mHandler.postDelayed(this, PERIOD);
            }
        };
    }

    private void periodicFetchNewSurveys() {
        mHandler.post(mRunnable);
    }

    private void stopFetchingNewSurveys() {
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void showList(List<Enquete> surveys) {
        this.surveys = surveys;
        surveyAdapter = new ParticipantSurveyAdapter(ctx, this.surveys, presenter);
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
        srl.setRefreshing(false);
    }

    @Override
    public void appendNew(List<Enquete> surveys) {
        Enquete.appendNew(this.surveys, surveys);
        surveyRecycleView.setAdapter(surveyAdapter);
        surveyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showStats(int surveyIndex) {
        surveys.get(surveyIndex).setAnimate(true);
        surveys.get(surveyIndex).setStatsVisible(true);
        surveyRecycleView.setAdapter(surveyAdapter);
        surveyAdapter.notifyDataSetChanged();

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
        stopFetchingNewSurveys();
    }

    @Override
    public void onResume() {
        super.onResume();
        periodicFetchNewSurveys();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopFetchingNewSurveys();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopFetchingNewSurveys();
    }
}
