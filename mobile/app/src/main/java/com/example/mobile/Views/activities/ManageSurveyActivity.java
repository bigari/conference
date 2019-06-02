package com.example.mobile.Views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.mobile.R;
import com.example.mobile.Repositories.SurveyRepository;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Option;
import com.example.mobile.Views.ViewInterfaces.CreateSurveyView;
import com.example.mobile.presenters.ManageSurveyPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ManageSurveyActivity extends AppCompatActivity implements CreateSurveyView {

    private AppCompatButton submit, updateTitle;
    private ImageButton moreOption;
    private LinearLayout optionContainer;
    private LayoutInflater inflater;
    private EditText editTitle;
    private int confId;
    private SharedPreferences prefs;
    private ManageSurveyPresenter presenter;
    private Enquete survey;
    private Intent incomingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_survey);
        this.incomingIntent = getIntent();
        confId = incomingIntent.getExtras().getInt("confId");

        if (survey == null) {
            survey = new Enquete();
        }

        inflater = LayoutInflater.from(ManageSurveyActivity.this); // 1
        moreOption = findViewById(R.id.button_survey_create_more_options);
        submit = findViewById(R.id.button_survey_create_submit);
        editTitle = findViewById(R.id.edittext_survey_create_title);
        updateTitle = findViewById(R.id.button_survey_change_title);


        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        optionContainer = findViewById(R.id.survey_create_options);

        presenter = new ManageSurveyPresenter(this, new SurveyRepository());

        moreOption.setOnClickListener(v->{
            View editOption = inflater.inflate(R.layout.edittext_option, null);
            optionContainer.addView(editOption);
        });


        if (incomingIntent.getStringExtra("type").equals("create")) {

            for (int i =0; i<3; i++) {
                View editOption = inflater.inflate(R.layout.edittext_option, null);
                optionContainer.addView(editOption);
            }

            submit.setOnClickListener(v->{
                createSurvey();
            });

        } else {

           layoutForUpdate();
            submit.setOnClickListener(v->{
                updateSurveyOptions();
            });
        }

    }

    private void layoutForUpdate(){
        submit.setText("Update Options");
        Gson gson = new Gson();


        this.survey = gson.fromJson(incomingIntent.getStringExtra("survey"), Enquete.class);
        editTitle.setText(survey.getIntituleEnquete());


        Type listOptionType = new TypeToken<List<Option>>(){}.getType();
        this.survey.setOptions(
                gson.fromJson(
                        incomingIntent.getStringExtra("options"),
                        listOptionType
                )
        );

        for(Option option : survey.getOptions()) {
            TextInputLayout editOption = (TextInputLayout) inflater.inflate(R.layout.edittext_option, null);
            editOption.getEditText().setText(option.getIntituleOption());
            optionContainer.addView(editOption);
        }

        updateTitle.setVisibility(View.VISIBLE);
        updateTitle.setOnClickListener(v->{
            updateSurveyTitle();
        });

    }

    private void collectOptions() {
        int count = optionContainer.getChildCount();
        View v = null;
        survey.setOptions(new ArrayList<>());
        for(int i=0; i<count; i++) {
            v = optionContainer.getChildAt(i);
            String optionTitle = ((TextInputLayout)v).getEditText().getText().toString();
            if ( optionTitle != null && optionTitle != "") {
                survey.getOptions().add(new Option(optionTitle));
            }
        }
    }

    private  void createSurvey () {
        String intituleEnquete = editTitle.getText().toString();
        if ( intituleEnquete == "") {
            //Afficher snackbar title blank
            return;
        }
        survey.setIntituleEnquete(intituleEnquete);
        collectOptions();
        presenter.createSurvey(survey, confId, prefs.getString("token", ""));
    }

    private  void updateSurveyTitle() {
        presenter.updateSurvey(survey);
    }

    private void updateSurveyOptions() {



        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("All votes will be lost. Do you really want to continue")
                .setPositiveButton("Yes", (dialog, which) -> {
                    collectOptions();
                    presenter.updateSurveyOptions(survey);
                }).setNegativeButton("No", null)
                .show();
    }

    @Override
    public void showErrorView(String error) {

    }

    @Override
    public void navToSurveysView() {
        finish();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }
}
