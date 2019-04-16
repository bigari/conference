package com.example.mobile.Repositories.apis;

import com.example.mobile.Repositories.models.Participant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ParticipantApi {

    @POST("api/participants")
    Call<Participant> createParticipant();

}
