package com.example.masato.slackapisample;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by masato on 16/12/21.
 */

public class SlackApiHolder {
    private static SlackApi INSTANCE;

    public static SlackApi get() {
        if (INSTANCE == null) {
            INSTANCE = createInstance();
        }

        return INSTANCE;
    }

    private static SlackApi createInstance() {
        return retrofit().create(SlackApi.class);
    }

    private static Retrofit retrofit() {
        final String ENDPOINT = "https://slack.com/";
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}
