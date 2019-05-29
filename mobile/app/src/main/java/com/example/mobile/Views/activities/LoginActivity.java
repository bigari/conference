package com.example.mobile.Views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConfListCache;
import com.example.mobile.Repositories.SpeakerRepository;
import com.example.mobile.Repositories.models.Speaker;
import com.example.mobile.Views.SpeakerLoginView;
import com.example.mobile.presenters.SpeakerLoginPresenter;

public class LoginActivity extends AppCompatActivity implements SpeakerLoginView {

    private AppCompatButton loginButton;
    private EditText emailInput, passwordInput;
    private SpeakerLoginPresenter presenter;
    private ProgressBar progressBar;
    private RelativeLayout layout;
    private RelativeLayout.LayoutParams params;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.login_email);
        passwordInput = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.btn_login);

        progressBar = new ProgressBar(LoginActivity.this,null,android.R.attr.progressBarStyleLarge);

        params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout = findViewById(R.id.login_relative_layout);

        loginButton.setOnClickListener( v-> {
           authenticate();
        });

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
    }

    protected void authenticate() {
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("email", emailInput.getText().toString());
        editor.apply();
        //Speaker.getCurrent().setPassword(passwordInput.getText().toString());
        layout.removeView(progressBar);
        layout.addView(progressBar, params);

        progressBar.setVisibility(View.VISIBLE);  //To show ProgressBar
        SpeakerRepository repository = new SpeakerRepository();
        presenter = new SpeakerLoginPresenter(LoginActivity.this, repository);
        presenter.login( new Speaker(emailInput.getText().toString(), passwordInput.getText().toString()));
    }

    @Override
    public void showLoginError() {
        progressBar.setVisibility(View.GONE);
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Connection failed")
                .setMessage("Please check your internet connection")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public void showLoginSuccess(Speaker speaker) {
        progressBar.setVisibility(View.GONE);
        if (speaker == null) {
            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Login Failed")
                    .setMessage("Email or password Incorrect")
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
            return;
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token", speaker.getToken());
        if (!prefs.getString("uid", "0").equals(speaker.getId().toString())) {
            ConfListCache.getInstance().clear();
            editor.putString("uid", speaker.getId().toString());
        }
        editor.apply();
        System.out.println(">>>>>>>>>>>>>>>>>>>>Speaker TOKEN>>>>>>>>>>>>>>>>>>>");
        System.out.println(speaker.getToken());
        System.out.println(">>>>>>>>>>>>>>>>>>>>Speaker ID>>>>>>>>>>>>>>>>>>>");
        System.out.println(speaker.getId());
        Intent intent = new Intent(LoginActivity.this, ConferenceListActivity.class);
        startActivity(intent);
    }
}
