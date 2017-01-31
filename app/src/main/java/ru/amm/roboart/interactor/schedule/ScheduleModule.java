package ru.amm.roboart.interactor.schedule;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ScheduleModule {

    @PerApplication
    @Provides
    ScheduleRepository provideScheduleRepository(Retrofit retrofit) {
        return new ScheduleRepository(retrofit);
    }

}