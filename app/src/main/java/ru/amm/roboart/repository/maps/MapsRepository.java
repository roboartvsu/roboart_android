package ru.amm.roboart.repository.maps;


import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.QueryBuilder;
import retrofit2.Retrofit;
import ru.amm.roboart.entity.Maps;
import rx.Observable;

public class MapsRepository {

    private MapsApi mapsApi;
    private Box<Maps> mapsBox;

    @Inject
    public MapsRepository(Retrofit retrofit, BoxStore boxStore) {
        mapsApi = retrofit.create(MapsApi.class);
        mapsBox = boxStore.boxFor(Maps.class);
    }

    private Observable<List<Maps>> getMapsFromApi() {
        return mapsApi.getMaps()
                .doOnNext(mapsList -> mapsBox.put(mapsList));
    }

    public Observable<List<Maps>> getMaps() {
        QueryBuilder<Maps> queryBuilder = mapsBox.query();
        List<Maps> mapsList = queryBuilder.build().find();
        if (mapsList.size() != 0) {
            return Observable.just(mapsList);
        } else {
            return getMapsFromApi();
        }
    }
}
