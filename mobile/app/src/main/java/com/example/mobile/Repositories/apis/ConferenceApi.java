package com.example.mobile.Repositories.apis;

import com.example.mobile.Repositories.models.Attachment;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Repositories.models.Questionnaire;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ConferenceApi {

    @GET("api/conferences")
    Call<List<Conference>> getConferences();

    @GET("api/conferences/{id}")
    Call<Conference> getConferenceById(@Path("id") int conferenceId);

    @GET("api/conferences/{id}/attachments")
    Call<List<Attachment>> getAttachments(@Path("id") int conferenceId);

    @GET("api/conferences/{id}/enquetes")
    Call<List<Enquete>> getSurveys(@Path("id") int conferenceId);

    @GET("api/conferences/{id}/questionnaires")
    Call<List<Questionnaire>> getQuestionnaire(@Path("id") int conferenceId);

    @Multipart
    @POST("api/conferences")
    Call<ResponseBody> createConference(@Body Conference conference);

    @Multipart
    @DELETE("api/conferences/{id}")
    Call<Void> deleteConference(@Body Conference conference);

    @DELETE("api/conferences/{id}/enquetes")
    Call<Void> deleteEnquetes(@Path("id") int conferenceId);

    @DELETE("api/conferences/{id}/questionnaires")
    Call<Void> deleteQuestionnaires(@Path("id") int conferenceId);

    @PUT("api/conferences/{id}")
    Call<Void> updateConference(@Path("id") int conferenceId, @Body Conference conference);

    // TODO define in loopback
    @GET("api/conferences/{id}/participants/count")
    Call<ResponseBody> getParticipantCount(@Path("id") int conferenceId);
}
