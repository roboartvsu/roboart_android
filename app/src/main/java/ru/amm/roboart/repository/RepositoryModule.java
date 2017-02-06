package ru.amm.roboart.repository;


import android.content.Context;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Module;
import dagger.Provides;
import io.objectbox.BoxStore;
import retrofit2.Retrofit;
import ru.amm.roboart.entity.MyObjectBox;
import ru.amm.roboart.repository.maps.MapsRepository;

@Module
public class RepositoryModule {

    @Provides
    @PerApplication
    MapsRepository provideMapsRepository(Retrofit retrofit, BoxStore boxStore) {
        return new MapsRepository(retrofit, boxStore);
    }

    @Provides
    @PerApplication
    BoxStore provideBoxStore(Context context) {
        return MyObjectBox.builder().androidContext(context).build();
    }
}
