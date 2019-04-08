package com.example.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.mobile.apis.ConferenceApi;
import com.example.mobile.models.Conference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getRetrofit();
        final ConferenceApi conference = retrofit.create(ConferenceApi.class);

        conference.getConferences().enqueue(new Callback<List<Conference>>() {
            @Override
            public void onResponse(Call<List<Conference>> call, Response<List<Conference>> response) {
                List<Conference> conferenceList = response.body();
                for(Conference conference : conferenceList){
                    Log.e(TAG, "onResponse: " + conferenceList.get(0).getNom());
                }
            }

            @Override
            public void onFailure(Call<List<Conference>> call, Throwable t) {
                Log.w(TAG, "onFailure: failed to load conference list", t);
            }
        });
    }
}
