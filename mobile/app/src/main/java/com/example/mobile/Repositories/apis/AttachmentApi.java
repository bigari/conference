package com.example.mobile.Repositories.apis;

import com.example.mobile.Repositories.models.Attachment;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AttachmentApi {

    @GET("api/attachments/{id}")
    Call<Attachment> getAttachment(@Path("id") int attachmentId);

    @DELETE("api/attachment/{id}")
    Call<Void> deleteAttachment(@Path("id") int attachmentId);

}
