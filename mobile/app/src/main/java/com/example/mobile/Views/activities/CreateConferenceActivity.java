package com.example.mobile.Views.activities;

import android.app.Activity;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Attachment;
import com.example.mobile.Views.CreateConferenceView;
import com.example.mobile.presenters.CreateConferencePresenter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CreateConferenceActivity extends AppCompatActivity implements CreateConferenceView {

    private static final String TAG = "CreateConferenceActivity";
    private TextInputLayout titleIL;
    private TextInputLayout startDateIL;
    private TextInputLayout endDateIL;


    private TextInputEditText titleV;
    private TextInputEditText startDateV;
    private TextInputEditText endDateV;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_conference);

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

        StartDatePickerListener startDatePickerListener = new StartDatePickerListener();
        EndDatePickerListener endDatePickerListener = new EndDatePickerListener();

        Date date = new Date();
        String now = new SimpleDateFormat("MMM dd, YYYY", Locale.ENGLISH).format(date);

        final DatePickerDialog dpd = DatePickerDialog.newInstance(null
                , date.getYear()
                , date.getMonth()
                , date.getDay());


        ConferenceRepository repository = new ConferenceRepository();
        final CreateConferencePresenter presenter = new CreateConferencePresenter(this,repository);

        titleV = findViewById(R.id.edittext_conference_create_title);
        startDateV  = findViewById(R.id.edittext_conference_create_start_date);
        endDateV  = findViewById(R.id.edittext_conference_create_end_date);

        startDateV.setText(now);
        endDateV.setText(now);
        FragmentManager manager = getSupportFragmentManager();
        dpd.show(manager, "");

        startDateV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    hideKeyboard();
                    dpd.setOnDateSetListener(startDatePickerListener);

                }
            }
        });

        endDateV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    hideKeyboard();
                    dpd.setOnDateSetListener(endDatePickerListener);
                    dpd.show();
                }
            }
        });


        titleIL = findViewById(R.id.inputlayout_conference_create_title);


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
        return titleV.getText().toString();
    }

    @Override
    public String getConfStartDate() {
        return startDateV.getText().toString();
    }

    @Override
    public String getConfEndDate() {
        return endDateV.getText().toString();
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    private class StartDatePickerListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            try{
                Date date = new SimpleDateFormat("dd-MM-YYYY", Locale.ENGLISH)
                        .parse(dayOfMonth + "-" + month + "-" + year);
                startDateV.setText(new SimpleDateFormat("MMMM dd, YYYY", Locale.ENGLISH).format(date));
            }catch(Exception ex){
                Log.e(TAG, "onDateSet: " + ex.getMessage(), ex);
            }
        }
    }

    private class EndDatePickerListener implements DatePickerDialog.OnDateSetListener {


        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            try{
                Date date = new SimpleDateFormat("dd-MM-YYYY", Locale.ENGLISH)
                        .parse(dayOfMonth + "-" + month + "-" + year);

                endDateV.setText(new SimpleDateFormat("MMMM dd, YYYY", Locale.ENGLISH).format(date));
            }catch(Exception ex){
                Log.e(TAG, "onDateSet: " + ex.getMessage(), ex);
            }
        }

    }

}


