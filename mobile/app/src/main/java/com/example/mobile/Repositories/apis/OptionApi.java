package com.example.mobile.Repositories.apis;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OptionApi {

    @DELETE("api/options/{id}")
    Call<Void> deleteOption(@Path("id") int optionId);

    // TODO define this endpoint (increments the voteCount)
    @PUT("api/options/{id}/increment")
    Call<Void> incrementVoteCount(@Path("id") int optionId);

    // TODO define this endpoint (decrements the voteCount)
    @PUT("api/options/{id}/decrement")
    Call<Void> decrementVoteCount(@Path("id") int optionId);

}
