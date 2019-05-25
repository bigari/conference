package com.example.mobile.Views.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Views.ViewInterfaces.CreateConferenceView;
import com.example.mobile.presenters.CreateConferencePresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private String title;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private DatePickerDialog dpd;

    @RequiresApi(api = Build.VERSION_CODES.N)
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

        titleV = findViewById(R.id.edittext_conference_create_title);
        startDateV  = findViewById(R.id.edittext_conference_create_start_date);
        endDateV  = findViewById(R.id.edittext_conference_create_end_date);
        submitBtn = findViewById(R.id.button_conference_create_submit);

        final StartDatePickerListener startDatePickerListener = new StartDatePickerListener();
        final EndDatePickerListener endDatePickerListener = new EndDatePickerListener();


        Calendar calendar = Calendar.getInstance();

        Date startDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = calendar.getTime();


        setStartDateV(startDate);
        setEndDateV(endDate);

        setStartDate(startDate.getTime());
        setEndDate(endDate.getTime());

        dpd = new DatePickerDialog(this);
        dpd.getDatePicker().setMinDate(calendar.getTimeInMillis());


        ConferenceRepository repository = new ConferenceRepository();
        final CreateConferencePresenter presenter = new CreateConferencePresenter(this,repository);


        startDateV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    hideKeyboard();
                    dpd.setOnDateSetListener(startDatePickerListener);
                    dpd.show();
                }


            }
        });

        startDateV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                dpd.setOnDateSetListener(startDatePickerListener);
                dpd.show();
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

        endDateV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                dpd.setOnDateSetListener(endDatePickerListener);
                dpd.show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createConference();
            }
        });
        titleIL = findViewById(R.id.inputlayout_conference_create_title);
    }

    @Override
    public void showProgressbar() {
    }
    @Override
    public void hideProgressbar() {
    }

    @Override
    public void showErrorView() {
        Toast.makeText(this, "An error has occured, please try again.", Toast.LENGTH_LONG).show();
    }

    @Override
    public String getConfTitle() {
        return titleV.getText().toString();
    }

    @Override
    public java.sql.Date getConfStartDate() {
        return startDate;
    }

    @Override
    public java.sql.Date getConfEndDate() {
        return endDate;
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private class StartDatePickerListener implements DatePickerDialog.OnDateSetListener {
        @SuppressLint("LongLogTag")
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            try{
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);

                view.updateDate(year, month, dayOfMonth);

                setStartDate(cal.getTimeInMillis());
                setStartDateV(cal.getTime());

            }catch(Exception ex){
                Log.e(TAG, "onDateSet: " + ex.getMessage(), ex);
            }
        }
    }

    private class EndDatePickerListener implements DatePickerDialog.OnDateSetListener {
        @SuppressLint("LongLogTag")
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            try{
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, dayOfMonth);

                view.updateDate(year, month, dayOfMonth);

                setEndDate(cal.getTimeInMillis());
                setEndDateV(cal.getTime());
            }catch(Exception ex){
                Log.e(TAG, "onDateSet: " + ex.getMessage(), ex);
            }
        }
    }

    private void setStartDateV(Date date){
        String FormattedDate = new SimpleDateFormat("MMM dd, YYYY", Locale.ENGLISH).format(date);
        startDateV.setText(FormattedDate);
    }

    private void setEndDateV(Date date){
        String FormattedDate = new SimpleDateFormat("MMM dd, YYYY", Locale.ENGLISH).format(date);
        endDateV.setText(FormattedDate);
    }

    private void setStartDate(long date){
        this.startDate = new java.sql.Date(date);
    }

    private void setEndDate(long date){
        this.endDate = new java.sql.Date(date);
    }

    @Override
    public void navToConfList(){
        startActivity(new Intent(this, ConferenceListActivity.class));
    }

}


