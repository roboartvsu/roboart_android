package ru.amm.roboart.interactor.maps;


import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.amm.roboart.model.map.Maps;
import ru.amm.roboart.repository.maps.MapsRepository;
import rx.Observable;

public class MapsInteractor {

    private MapsRepository mapsRepository;

    @Inject
    public MapsInteractor(MapsRepository mapsRepository) {
        this.mapsRepository = mapsRepository;
    }

    public Observable<List<Maps>> getMaps() throws SQLException {
       return mapsRepository.getMaps();
    }
}
