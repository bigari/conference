package com.example.mobile.Views.activities;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

import com.example.mobile.R;
import com.example.mobile.Repositories.models.Participant;

public class LandingActivity extends AppCompatActivity {

    private Button join, login, signup;
    private TextInputEditText emailEdit, codeEdit;
    private TableRow authenticationRow, workspaceRow;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        authenticationRow = findViewById(R.id.landing_authentication_row);
        workspaceRow = findViewById(R.id.landing_authentication_row);
        join = findViewById(R.id.landing_join);
        login = findViewById(R.id.landing_login);
        signup = findViewById(R.id.landing_signup);
        emailEdit = findViewById(R.id.landing_email);
        codeEdit = findViewById(R.id.landing_code);

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        if (prefs.getString("token", "") != "") {
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
    }


}
