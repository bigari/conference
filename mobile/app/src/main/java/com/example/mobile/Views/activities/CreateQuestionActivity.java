package com.example.mobile.Views.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    private ViewGroup root;


    private CreateQuestionPresenter presenter;
    private int confId;

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
        root = findViewById(R.id.layout_root);

        confId = getIntent().getIntExtra("confId", 0);
        QuestionRepository repo = new QuestionRepository();
        presenter = new CreateQuestionPresenter(this, repo);


        createQuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameV.getText().toString();
                String question = questionV.getText().toString();
                hideKeyboard();
                presenter.createQuestion(username, question, confId);
            }
        });

    }

    @Override
    public void showErrorView(String message) {
        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void navToQuestsView() {
        finish();
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress()
    {
    }
}
