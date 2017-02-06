package ru.amm.roboart.interactor.maps;


import java.util.List;

import javax.inject.Inject;

import ru.amm.roboart.entity.Maps;
import ru.amm.roboart.repository.maps.MapsRepository;
import rx.Observable;

public class MapsInteractor {

    private MapsRepository mapsRepository;

    @Inject
    public MapsInteractor(MapsRepository mapsRepository) {
        this.mapsRepository = mapsRepository;
    }

    public Observable<List<Maps>> getMaps(){
       return mapsRepository.getMaps();
    }
}
