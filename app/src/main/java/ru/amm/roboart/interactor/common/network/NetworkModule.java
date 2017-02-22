package ru.amm.roboart.interactor.common.network;

import android.content.Context;

import com.agna.ferro.mvp.component.scope.PerApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.amm.roboart.BuildConfig;

/**
 * Модуль для работы с сетевыми запросами через Retrofit
 */
@Module
public class NetworkModule {

    private final static int READ_TIMEOUT_SECONDS = 15;
    private final static int CONNECT_TIMEOUT_SECONDS = 15;

    private Context context;
    private String baseUrl;

    public NetworkModule(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @PerApplication
    @Provides
    Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @PerApplication
    @Provides
    Cache providesCache() {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @PerApplication
    @Provides
    HttpLoggingInterceptor provideHTTPLogginInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return interceptor;
    }

    @PerApplication
    @Provides
    OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @PerApplication
    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}