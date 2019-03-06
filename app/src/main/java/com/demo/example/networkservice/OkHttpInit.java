package com.demo.example.networkservice;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpInit {

    private static final String KEY = "$2a$10$4juhafP3gPiRY/GznlAfYeex4qnXVqD4/zGl6IXLaP70PxdUfI3ue";
    private static final String SECRET_KEY = "secret-key";

    public Retrofit getHttpClient() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader(SECRET_KEY, KEY)
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        return new Retrofit.Builder()
                .baseUrl(NetworkInterface.API_HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

}