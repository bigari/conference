package com.example.mobile.Views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.ParticipantRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Repositories.models.Participant;
import com.example.mobile.Views.ViewInterfaces.LandingView;
import com.example.mobile.presenters.ParticipantJoinPresenter;
import com.example.mobile.presenters.participant.ParticipantPresenter;

public class LandingActivity extends AppCompatActivity implements LandingView {

    private Button join, login, signup, goWorkspace;
    private TextInputEditText emailEdit, codeEdit;
    private TableRow authenticationRow, workspaceRow;
    private ViewGroup root;
    private ProgressBar progressBar;

    private SharedPreferences prefs;

    private ParticipantPresenter participantPresenter;

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
        progressBar = findViewById(R.id.progressbar);
        root = findViewById(R.id.layout_root);

        goWorkspace = findViewById( R.id.landing_go_workspace);

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        ParticipantJoinPresenter presenter = new ParticipantJoinPresenter(
                new ConferenceRepository(), this
        );

        toogleActions();
        participantPresenter = new  ParticipantPresenter(
                this,
                new ParticipantRepository()
        );

        if (prefs.getString("accessKey", "") == "") {
           Participant.create(prefs);
           participantPresenter.create(Participant.current);
        } else {
            Participant.retrieve(prefs);
        }

        join.setOnClickListener( v -> {
            hideKeyboard();
            String email = emailEdit.getText().toString().trim();
            String code = codeEdit.getText().toString().trim();
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
        if (prefs.getString("participantId", "") != "") {
            participantPresenter.create(Participant.current);
        }

        Intent intent = new Intent(this, ConferenceActivity.class);
        intent.putExtra("role", "participant");
        intent.putExtra("confTitle", conf.getTitle());
        intent.putExtra("accessCode", conf.getAccessCode());
        intent.putExtra("confId", conf.getId());
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgresss() {
        this.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showError(String error) {
        Snackbar.make(root, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setParticipantId(Integer id) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("participantId", id.toString());
        System.out.println(">>>>>>>>>>>"+ "Participant("+id+", "+ Participant.current.getAccessKey()+ ") >>>>>>>>>");
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
