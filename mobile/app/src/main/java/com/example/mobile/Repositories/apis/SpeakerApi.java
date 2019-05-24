package com.example.mobile.Repositories.apis;

/**
 * Created by neron on 5/23/19.
 */

import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Repositories.models.User;

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


public interface PresenterApi {
    @POST("api/presenters/login")
    Call<User> login(@Body User user);
}
