package ru.amm.roboart.repository.maps;

import java.util.List;

import retrofit2.http.GET;
import ru.amm.roboart.model.map.Maps;
import rx.Observable;

public interface MapsApi {

    @GET("/maps")
    Observable<List<Maps>> getMaps();
}
