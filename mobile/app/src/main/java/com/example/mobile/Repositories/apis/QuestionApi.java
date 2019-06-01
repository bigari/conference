package com.example.mobile.Repositories.apis;

import com.example.mobile.Repositories.models.Question;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface QuestionApi {

    @POST("api/questions")
    Call<Void> create(@Body Question quest);

    @DELETE("api/conferences/{confId}/questions/{questId}")
    Call<Void> deleteQuestion(@Path("confId") int confId, @Path("questId") int questId,
                                @Header("Authorization") String token);
}
