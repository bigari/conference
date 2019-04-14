package com.example.mobile.Repositories.apis;


import com.example.mobile.Repositories.models.Questionnaire;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface QuestionnaireApi {


    @POST("api/conferences/{id}/questionnaires")
    Call<ResponseBody> createQuestionnaire(@Path("id") int conferenceId, @Body Questionnaire questionnaire);

    @PUT("api/questionnaires/{id}")
    Call<Void> updateQuestionnaire(@Path("id") int questionnaireId, @Body Questionnaire questionnaire);

    @DELETE("api/questionnaires/{id}}")
    Call<Void> deleteQuestionnaire(@Path("id") int questionnaireId);




}
