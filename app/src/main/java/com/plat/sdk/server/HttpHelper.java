package com.plat.sdk.server;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ydong
 */
public class HttpHelper {
    private static final String TAG = "HttpHelper";
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;
    private Interceptor headInterceptor;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;

    private HttpHelper() {
        httpClientBuilder = new OkHttpClient.Builder();
        initInterceptor();
        httpClientBuilder
                .addNetworkInterceptor(headInterceptor)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectionPool(
                        new ConnectionPool(4, 5, TimeUnit.SECONDS)
                );
    }

    private void initInterceptor() {
        headInterceptor = chain -> {
            Request newRequest = chain.request().newBuilder().addHeader("Gs-Client-Access-Key", "123456").addHeader("Gs-Client-Type", "MOBILE_APP").addHeader("Gs-Language-Type", "EN").build();
            return chain.proceed(newRequest);
        };
        loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public <T> T createService(final Class<T> service) {
        return createService(service, CaseApi.API);
    }

    public <T> T createService(final Class<T> service, String baseUrl) {
        return createService(service, baseUrl, DEFAULT_TIMEOUT);
    }

    public <T> T createService(final Class<T> service, String baseUrl, int timeOut) {
        httpClientBuilder.connectTimeout(timeOut, TimeUnit.SECONDS).readTimeout(timeOut, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(service);
    }

    public <T> T createLongService(final Class<T> service, String baseUrl, int timeOut) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeOut, TimeUnit.SECONDS)
                .addNetworkInterceptor(headInterceptor)
                .retryOnConnectionFailure(true)
                .readTimeout(timeOut, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder().client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(service);
    }

    private static class SingleHolder {
        private static final HttpHelper INSTANCE = new HttpHelper();
    }

    public static HttpHelper getInstance() {
        return SingleHolder.INSTANCE;
    }
}

