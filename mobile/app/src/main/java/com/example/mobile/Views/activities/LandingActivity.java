package com.example.mobile.Views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import com.example.mobile.R;

public class LandingActivity extends AppCompatActivity {

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

        if (!prefs.getString("token", "").equals("")) {
            authenticationRow.setVisibility(View.GONE);
            workspaceRow.setVisibility(View.VISIBLE);
        }

        if (prefs.getString("accessKey", "") == "") {
           // Participant.create(prefs);
            //uncomment once request to server is implemented
        }

        join.setOnClickListener( v -> {
            //
        });

        login.setOnClickListener(v-> {
            Intent intent = new Intent(LandingActivity.this,
                    LoginActivity.class);
            startActivity(intent);
        });

        goWorkspace.setOnClickListener( v -> {
            Intent intent = new Intent(this,
                    ConferenceListActivity.class);
            startActivity(intent);
        });

    }


}
