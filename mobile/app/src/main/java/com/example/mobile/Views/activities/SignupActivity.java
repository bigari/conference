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
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConfListCache;
import com.example.mobile.Repositories.SpeakerRepository;
import com.example.mobile.Repositories.models.Speaker;
import com.example.mobile.Views.ViewInterfaces.speaker.SpeakerSignupView;
import com.example.mobile.presenters.speaker.SpeakerLoginPresenter;
import com.example.mobile.presenters.speaker.SpeakerSignupPresenter;

public class SignupActivity extends AppCompatActivity implements SpeakerSignupView {

    private AppCompatButton signupButton;
    private EditText emailInput, passwordInput, usernameInput;
    private SpeakerSignupPresenter presenter;
    private ProgressBar progressBar;
    private RelativeLayout layout;
    private RelativeLayout.LayoutParams params;
    private SharedPreferences prefs;
    private TextView loginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emailInput = findViewById(R.id.signup_email);
        passwordInput = findViewById(R.id.signup_password);
        usernameInput = findViewById(R.id.signup_username);
        signupButton = findViewById(R.id.btn_signup);
        loginLink = findViewById(R.id.signup_link_login);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        loginLink.setOnClickListener( v-> {
            Intent intent = new Intent(
                    SignupActivity.this,
                    LoginActivity.class
            );
            startActivity(intent);
        });

        progressBar = new ProgressBar(SignupActivity.this,null,android.R.attr.progressBarStyleLarge);

        params = new RelativeLayout.LayoutParams(100,100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout = findViewById(R.id.signup_relative_layout);

        signupButton.setOnClickListener( v-> {
            register();
        });

    }

    private void register() {
        layout.removeView(progressBar);
        layout.addView(progressBar, params);

        progressBar.setVisibility(View.VISIBLE);  //To show ProgressBar
        SpeakerRepository repository = new SpeakerRepository();
        presenter = new SpeakerSignupPresenter(SignupActivity.this, repository);
        presenter.signup(
                new Speaker(
                        emailInput.getText().toString(),
                        usernameInput.getText().toString(),
                        passwordInput.getText().toString()
                )
        );

    }

    @Override
    public void showSignupError() {
        progressBar.setVisibility(View.GONE);
        new AlertDialog.Builder(SignupActivity.this)
                .setTitle("Connection failed")
                .setMessage("Please check your internet connection")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public void showSignupSuccess(Speaker speaker) {
        progressBar.setVisibility(View.GONE);
        if (speaker == null) {
            new AlertDialog.Builder(SignupActivity.this)
                    .setTitle("Signup Failed")
                    .setMessage("Email or username already in use")
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
            return;
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token", speaker.getToken());
        if (!prefs.getString("uid", "0").equals(speaker.getId().toString())) {
            ConfListCache.getInstance().clear();
            editor.putString("uid", speaker.getId().toString());
            editor.putString("email", emailInput.getText().toString());
            editor.putString("username", usernameInput.getText().toString());
            editor.apply();
        }
        editor.apply();
        System.out.println(">>>>>>>>>>>>>>>>>>>>Speaker TOKEN>>>>>>>>>>>>>>>>>>>");
        System.out.println(speaker.getToken());
        System.out.println(">>>>>>>>>>>>>>>>>>>>Speaker ID>>>>>>>>>>>>>>>>>>>");
        System.out.println(speaker.getId());
        Intent intent = new Intent(SignupActivity.this, ConferenceListActivity.class);
        startActivity(intent);
    }
}
