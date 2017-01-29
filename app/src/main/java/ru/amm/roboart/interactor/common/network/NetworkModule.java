package ru.amm.roboart.interactor.common.network;

import com.agna.ferro.mvp.component.scope.PerApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module()
public class NetworkModule {

    public static final String HTTP_LOG_TAG = "OkHttp";

    @Provides
    @PerApplication
    Retrofit provideRetrofit(OkHttpClient okHttpClient,
                             Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ServerUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @PerApplication
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @PerApplication
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Timber.tag(HTTP_LOG_TAG).d(message));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
}