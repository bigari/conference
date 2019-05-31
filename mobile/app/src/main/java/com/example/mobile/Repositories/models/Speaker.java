package com.example.mobile.Repositories.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by neron on 5/23/19.
 */

public class Speaker {

    @Expose(deserialize = false)
    @SerializedName("email")
    private String email;
    @Expose(deserialize = false)
    @SerializedName("password")
    private String password;


    @Expose
    @SerializedName("username")
    private String username;

    @Expose(serialize = false)
    @SerializedName("id")
    private String token;
    @Expose(serialize = false, deserialize = true)
    @SerializedName("userId")
    private Integer id;


    /*private static Speaker current;

    public static Speaker getCurrent () {
        if (current == null) {
            current = new Speaker();
        }
        return current;
    }*/

    public Speaker(String email , String password) {
        this.email = email;
        this.password = password;
    }

    public Speaker(String email , String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
