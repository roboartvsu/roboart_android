package ru.amm.roboart.repository.maps;


import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import ru.amm.roboart.model.map.Maps;
import ru.amm.roboart.model.map.MapsDao;
import ru.amm.roboart.repository.DbHelper;
import rx.Observable;

public class MapsRepository {

    private MapsApi mapsApi;
    private MapsDao dao;

    @Inject
    public MapsRepository(Retrofit retrofit, DbHelper dbHelper) {
        mapsApi = retrofit.create(MapsApi.class);

        try {
            dao = DaoManager.createDao(dbHelper.getConnectionSource(), Maps.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Observable<List<Maps>> getMapsFromApi() {
        return mapsApi.getMaps()
                .doOnNext(mapsList -> {
                    try {
                        dao.create(mapsList);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    public Observable<List<Maps>> getMaps() throws SQLException {
        List<Maps> mapsList = dao.queryForAll();
        if (mapsList.size() != 0) {
            return Observable.just(mapsList);
        } else {
            return getMapsFromApi();
        }
    }
}
