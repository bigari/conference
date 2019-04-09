package com.example.mobile.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mobile.R;
import com.example.mobile.RetrofitClient;
import com.example.mobile.apis.ConferenceApi;
import com.example.mobile.models.Conference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConferenceListActivity extends AppCompatActivity {

    public static final String TAG = "ConferenceListActivity";
    Retrofit retrofit = RetrofitClient.getRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference_list);

        final ConferenceApi conference = retrofit.create(ConferenceApi.class);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview_conference_list);

        conference.getConferences().enqueue(new Callback<List<Conference>>() {
            @Override
            public void onResponse(Call<List<Conference>> call, Response<List<Conference>> response) {
                List<Conference> conferences = response.body();
                ConferenceListAdapter adapter = new ConferenceListAdapter(ConferenceListActivity.this, conferences);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ConferenceListActivity.this, LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onFailure(Call<List<Conference>> call, Throwable t) {
                Log.e(TAG, "onFailure: failed to load conference list", t);
            }
        });
    }
}
