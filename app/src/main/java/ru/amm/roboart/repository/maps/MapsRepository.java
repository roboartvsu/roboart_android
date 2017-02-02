package ru.amm.roboart.repository.maps;


import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import ru.amm.roboart.entity.Maps;
import rx.Observable;

public class MapsRepository {

    private MapsApi mapsApi;

    @Inject
    public MapsRepository(Retrofit retrofit) {
        mapsApi = retrofit.create(MapsApi.class);
    }

    public Observable<List<Maps>> getMapsFromApi() {
        return mapsApi.getMaps();
    }
}
