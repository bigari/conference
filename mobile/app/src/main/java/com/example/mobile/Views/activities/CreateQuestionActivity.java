package com.example.mobile.Views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.QuestionRepository;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Views.ViewInterfaces.CreateQuestionView;
import com.example.mobile.presenters.CreateQuestionPresenter;

public class CreateQuestionActivity extends AppCompatActivity implements CreateQuestionView {

    private Button createQuestBtn;
    private EditText questionV;
    private EditText usernameV;
    private CreateQuestionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        createQuestBtn = findViewById(R.id.button_create_question);
        usernameV = findViewById(R.id.edittext_question_username);
        questionV = findViewById(R.id.edittext_question_content);


        QuestionRepository repo = new QuestionRepository();
        presenter = new CreateQuestionPresenter(this, repo);


        createQuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void navToQuestsView() {

    }
}
