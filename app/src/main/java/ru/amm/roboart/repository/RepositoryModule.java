package ru.amm.roboart.repository;


import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.amm.roboart.repository.maps.MapsRepository;

@Module
public class RepositoryModule {

    @Provides
    @PerApplication
    MapsRepository provideMapsRepository(Retrofit retrofit) {
        return new MapsRepository(retrofit);
    }
}
