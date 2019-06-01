package com.example.mobile.Repositories.models;

import android.content.SharedPreferences;

import com.example.mobile.utils.RandomString;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Participant {
    @Expose(deserialize = false)
    @SerializedName("accessKey")
    private String accessKey;
    @Expose(serialize = false)
    @SerializedName("id")
    private Integer id;


    public Participant(){}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public static Participant current = new Participant();

    public static void create (SharedPreferences prefs) {
        current.accessKey =  RandomString.generate(32);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("accessKey", current.accessKey).apply();
        // send to server and get Id
    }

    public static void retrieve (SharedPreferences prefs) {
        current.accessKey =  prefs.getString("accessKey", "");
        current.id = Integer.parseInt(
                prefs.getString
                (
                    "participantId",
                    "0"
                )
        );

        if(current.accessKey == "") {
            create(prefs);
            return;
        }
        current.id = Integer.parseInt( prefs.getString("participantId", "0"));
    }
}
