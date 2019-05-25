package com.example.mobile.Repositories.apis;

import com.example.mobile.Repositories.models.Question;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface QuestionApi {

    @GET("api/conferences/{id}/questions?filter[order]=timestamp%20DESC")
    Call<List<Question>> getQuestions(@Path("id") int confId);

    @GET("api/participants/{id)/questions?filter[order]=timestamp%20DESC")
    Call<List<Question>> getQuestionsByUser(@Path("id") int participantId);

    @DELETE("api/questions/{id}")
    Call<Void> deleteQuestion(@Path("id") int questionId);
}
