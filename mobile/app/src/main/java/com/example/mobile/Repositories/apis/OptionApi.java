package com.example.mobile.Repositories.apis;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OptionApi {

    @DELETE("api/options/{id}")
    Call<Void> deleteOption(@Path("id") int optionId);

    @PUT("api/options/{id}/add")
    Call<Void> vote(@Path("id") int optionId);

}
