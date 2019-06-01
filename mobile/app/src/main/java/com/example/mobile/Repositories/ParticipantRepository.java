package com.example.mobile.Repositories;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.apis.ParticipantApi;
import com.example.mobile.Repositories.apis.ParticipantApi;
import com.example.mobile.Repositories.models.Participant;
import com.example.mobile.Repositories.models.Participant;
import com.example.mobile.RetrofitClient;

import retrofit2.Call;
import retrofit2.Response;

public class ParticipantRepository {


    private final ParticipantApi participantApi;

    public ParticipantRepository(){
        this.participantApi = RetrofitClient.getRetrofit().create(ParticipantApi.class);
    }

    public void create(Participant participant, final Callback<Participant> callback) {
        System.out.println(">>>>>>>>>>>Creating: "+ participant.getAccessKey());
        participantApi.createParticipant(participant).enqueue(new retrofit2.Callback<Participant>() {
            @Override
            public void onResponse(Call<Participant> call, Response<Participant> response) {
                System.out.println(">>>>>>>>>>>Participant Created: "+ participant.getId()+ " :: "+ participant.getAccessKey());
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Participant> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    

}
