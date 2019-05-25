package com.example.mobile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();
            Gson gson = builder
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit;
        }
        return retrofit;
    }

}
