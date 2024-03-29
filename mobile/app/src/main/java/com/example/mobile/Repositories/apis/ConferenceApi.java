package com.example.mobile.Repositories.apis;

import com.example.mobile.Repositories.models.Attachment;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Participant;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Repositories.models.Questionnaire;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ConferenceApi {


    @GET("api/speakers/{id}/conferences?filter[order]=dateFin%20DESC&filter[include]=enquetes&filter[include]=questionnaires&filter[include]=questions")
    Call<List<Conference>> getConferences(@Path("id") int userId,
                                          @Header("Authorization") String token);

    @GET("api/conferences/{id}/questions?filter[order]=timestamp%20DESC")
    Call<List<Question>> getQuestions(@Path("id") int confId);


    @GET("api/conferences/{id}/enquetes")
    Call<List<Enquete>> getSurveys(@Path("id") int conferenceId);

    @GET("api/conferences/{id}/questionnaires")
    Call<List<Questionnaire>> getQuestionnaire(@Path("id") int conferenceId);

    @POST("api/conferences")
    Call<Conference> createConference(@Body Conference conference,
                                      @Header("Authorization") String token);

    @DELETE("api/conferences/{id}")
    Call<Void> deleteConference(@Path("id") int conferenceId,
                               @Header("Authorization") String token);

    @DELETE("api/conferences/{id}/questions")
    Call<Void> deleteQuestions(@Path("id") int confId,
                                @Header("Authorization") String token);

    @DELETE("api/conferences/{id}/enquetes")
    Call<Void> deleteEnquetes(@Path("id") int conferenceId);

    @DELETE("api/conferences/{id}/questionnaires")
    Call<Void> deleteQuestionnaires(@Path("id") int conferenceId);

    @GET("api/conferences/join")
    Call<Conference> joinConference(@QueryMap Map<String, String> filters);


}
