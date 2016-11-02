package com.ludovic.crespeau.mangareader.api;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jeromeheissler on 20/07/2016.
 */

public class AuthenticatedInterceptor implements Interceptor {

    private Context appContext;

    @Inject
    public AuthenticatedInterceptor(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        return chain.proceed(originalRequest);

    }
}
