package com.example.mobile.Views.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mobile.R;
import com.example.mobile.Views.ConferenceView;
import com.example.mobile.Repositories.models.Conference;

public class ConferenceActivity extends AppCompatActivity implements ConferenceView {
    private LinearLayout surveyContainer;
    private LinearLayout quizContainer;
    private LinearLayout questionContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);
//        surveyContainer = (LinearLayout) findViewById(R.id.survey_container);
//        quizContainer = (LinearLayout) findViewById(R.id.quiz_container);
//        questionContainer = (LinearLayout) findViewById(R.id.question_container);
//        BottomNavigationView bottomNavigationView = (BottomNavigationView)
//                findViewById(R.id.conference_bottom_navigation);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.action_survey:
//                                surveyContainer.setVisibility(View.VISIBLE);
//                                quizContainer.setVisibility(View.GONE);
//                                questionContainer.setVisibility(View.GONE);
//                                break;
//                            case R.id.action_quiz:
//                                surveyContainer.setVisibility(View.GONE);
//                                quizContainer.setVisibility(View.VISIBLE);
//                                questionContainer.setVisibility(View.GONE);
//                                break;
//                            case R.id.action_question:
//                                surveyContainer.setVisibility(View.GONE);
//                                quizContainer.setVisibility(View.GONE);
//                                questionContainer.setVisibility(View.VISIBLE);
//                        }
//                        return true;
//                    }
//                });
    }

    @Override
    public void showConference(Conference conference) {

    }

    @Override
    public void showError() {

    }
}
