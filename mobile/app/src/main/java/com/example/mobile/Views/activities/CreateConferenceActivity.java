package com.example.mobile.Views.activities;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Attachment;
import com.example.mobile.Views.CreateConferenceView;
import com.example.mobile.presenters.CreateConferencePresenter;

public class CreateConferenceActivity extends AppCompatActivity implements CreateConferenceView {

    private TextInputLayout titleIL;
    private TextInputLayout descriptionIL;
    private TextInputLayout dateIL;
    private TextInputLayout addressIL;


    private TextInputEditText titleView;
    private TextInputEditText descriptionView;
    private TextInputEditText dateView;
    private TextInputEditText addressView;
    private Button validateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_conference);

        ConferenceRepository repository = new ConferenceRepository();
        final CreateConferencePresenter presenter = new CreateConferencePresenter(this,repository);

        titleView = findViewById(R.id.edittext_conference_create_title);
        descriptionView = findViewById(R.id.edittext_conference_create_description);
        dateView = findViewById(R.id.edittext_conference_create_date);
        addressView = findViewById(R.id.edittext_conference_create_address);
        validateView = findViewById(R.id.button_conference_create_validate);

        titleIL = findViewById(R.id.inputlayout_conference_create_title);
        descriptionIL = findViewById(R.id.inputlayout_conference_create_description);
        dateIL = findViewById(R.id.inputlayout_conference_create_date);
        addressIL = findViewById(R.id.inputlayout_conference_create_address);

        validateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createConference();
            }
        });

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void displayForm() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void addAttachmentField(Attachment attachment) {

    }

    @Override
    public String getConfTitle() {
        return titleView.getText().toString();
    }

    @Override
    public String getConfDescription() {
        return descriptionView.getText().toString();
    }

    @Override
    public String getConfAddress() {
        return addressView.getText().toString();
    }

    @Override
    public String getConfDate() {
        return dateView.getText().toString();
    }
}


