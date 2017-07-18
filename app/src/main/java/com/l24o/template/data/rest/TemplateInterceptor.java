package com.l24o.template.data.rest;

import android.text.TextUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Alexander Popov on 11/07/2017.
 */

public class TemplateInterceptor implements Interceptor {

    private final AuthProvider authProvider;

    public TemplateInterceptor(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            final Request request;
            if (TextUtils.isEmpty(authProvider.getAuthToken())) {
                request = chain.request();
            } else {
                request = chain.request()
                        .newBuilder()
                        .addHeader("X-Auth-Token", authProvider.getAuthToken())
                        .build();
            }
            return chain.proceed(request);
        } catch (SocketTimeoutException e) {
            throw new IOException("Не получилось установить связь с сервером", e);
        }
    }
}
