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
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface ConferenceApi {

    @GET("api/users/{id}/conferences?filter[order]=dateFin%20DESC")
    Call<List<Conference>> getConferences(@Path("id") int userId);

    @GET("api/conferences/{id}")
    Call<Conference> getConferenceById(@Path("id") int conferenceId);

    @GET("api/conferences/{id}/attachments")
    Call<List<Attachment>> getAttachments(@Path("id") int conferenceId);

    @GET("api/conferences/{id}/enquetes")
    Call<List<Enquete>> getSurveys(@Path("id") int conferenceId);

    @GET("api/conferences/{id}/questionnaires")
    Call<List<Questionnaire>> getQuestionnaire(@Path("id") int conferenceId);

    @POST("api/conferences")
    Call<Conference> createConference(@Body Conference conference);

    @DELETE("api/conferences/{id}")
    Call<Void> deleteConference(@Path("id") int conferenceId);

    @DELETE("api/conferences/{id}/enquetes")
    Call<Void> deleteEnquetes(@Path("id") int conferenceId);

    @DELETE("api/conferences/{id}/questionnaires")
    Call<Void> deleteQuestionnaires(@Path("id") int conferenceId);

    // TODO define in loopback
    @GET("api/conferences/{id}/participants/count")
    Call<ResponseBody> getParticipantCount(@Path("id") int conferenceId);

    // TODO define in loopback
    @POST("api/conferences/{id}/join")
    Call<Void> joinConference(@Path("id") int conferenceId, int participantId);

    // TODO define in loopback
    @POST("api/conferences/{id}/leave")
    Call<Void> leaveConference(@Path("id") int conferenceId, int participantId);

}
