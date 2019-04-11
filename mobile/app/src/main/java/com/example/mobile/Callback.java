package com.example.mobile;

public interface Callback<T> {

    void onSuccess(T value);
    void onError(Throwable error);
}
