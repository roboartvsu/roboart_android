package ru.amm.roboart.interactor.schedule;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 */
public class ScheduleRepository {

    private static final String TAG = "ScheduleRepository";

    //private NewsApi newsApi;

    @Inject
    public ScheduleRepository(Retrofit retrofit) {
        //newsApi = retrofit.create(NewsApi.class);
    }
}