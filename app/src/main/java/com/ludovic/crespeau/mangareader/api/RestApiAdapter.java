package com.ludovic.crespeau.mangareader.api;

import com.ludovic.crespeau.mangareader.Constants;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ludovic on 24/09/16.
 */
public class RestApiAdapter {

    static Retrofit sharedInstance = null;

    private AuthenticatedInterceptor authenticatedInterceptor;
    private static final Object lock = new Object();

    @Inject
    public RestApiAdapter(AuthenticatedInterceptor authenticatedInterceptor) {
        this.authenticatedInterceptor = authenticatedInterceptor;
    }

    public Retrofit getInstance() {
        synchronized (lock) {
            if (sharedInstance == null) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(authenticatedInterceptor)
                        .addInterceptor(logging)
                        .build();

                sharedInstance = new Retrofit.Builder()
                        .baseUrl(Constants.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
            }
        }
        return sharedInstance;
    }

}
