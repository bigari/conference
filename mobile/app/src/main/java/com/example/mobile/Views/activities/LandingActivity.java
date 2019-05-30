package com.example.mobile.Views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ViewInterfaces.LandingView;
import com.example.mobile.presenters.ParticipantJoinPresenter;

public class LandingActivity extends AppCompatActivity implements LandingView {

    private Button join, login, signup, goWorkspace;
    private TextInputEditText emailEdit, codeEdit;
    private TableRow authenticationRow, workspaceRow;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        authenticationRow = findViewById(R.id.landing_authentication_row);
        workspaceRow = findViewById(R.id.landing_workspace_row);
        join = findViewById(R.id.landing_join);
        login = findViewById(R.id.landing_login);
        signup = findViewById(R.id.landing_signup);
        emailEdit = findViewById(R.id.landing_email);
        codeEdit = findViewById(R.id.landing_code);

        goWorkspace = findViewById( R.id.landing_go_workspace);

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        ParticipantJoinPresenter presenter = new ParticipantJoinPresenter(
                new ConferenceRepository(), this
        );

        toogleActions();

        if (prefs.getString("accessKey", "") == "") {
           // Participant.create(prefs);
            //uncomment once request to server is implemented
        }

        join.setOnClickListener( v -> {
            String email = emailEdit.getText().toString();
            String code = codeEdit.getText().toString();
            presenter.joinConf(email, code);
        });

        login.setOnClickListener(v-> {
            Intent intent = new Intent(LandingActivity.this,
                    LoginActivity.class);
            startActivity(intent);
        });

        signup.setOnClickListener(v-> {
            Intent intent = new Intent(LandingActivity.this,
                    SignupActivity.class);
            startActivity(intent);
        });

        goWorkspace.setOnClickListener( v -> {
            Intent intent = new Intent(this,
                    ConferenceListActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        toogleActions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        toogleActions();
    }

    private void toogleActions() {
        if (!prefs.getString("token", "").equals("")) {
            authenticationRow.setVisibility(View.GONE);
            workspaceRow.setVisibility(View.VISIBLE);
        } else {
            authenticationRow.setVisibility(View.VISIBLE);
            workspaceRow.setVisibility(View.GONE);
        }
    }

    @Override
    public void navToConf(Conference conf) {
        Intent intent = new Intent(this, ConferenceActivity.class);
        intent.putExtra("role", "participant");
        intent.putExtra("confTitle", conf.getTitle());
        intent.putExtra("confId", conf.getId());

        startActivity(intent);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgresss() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
