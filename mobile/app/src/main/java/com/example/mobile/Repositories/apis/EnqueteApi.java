package com.example.mobile.Repositories.apis;

import com.example.mobile.Repositories.models.Enquete;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EnqueteApi {

    // TODO define in loopback (create enquete and corresponding options)
    @POST("api/conferences/{id}/enquetes")
    Call<ResponseBody> createEnquete(@Path("id") int conferenceId, @Body Enquete enquete);

    @DELETE("api/enquetes/{id}")
    Call<Void> deleteEnquete(@Path("id") int enqueteId);

    @PUT("api/enquetes/{id}")
    Call<Void> updateEnquetes(@Path("id") int enqueteId, @Body Enquete enquete);

    // TODO define in loopback
    @POST("api/enquetes/{id}/start")
    Call<Void> startEnquete(@Path("id") int enqueteId);

    // TODO define in loopback
    @POST("api/enquetes/{id}/stop")
    Call<Void> stopEnquete(@Path("id") int enqueteId);

}
