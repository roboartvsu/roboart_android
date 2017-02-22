package ru.amm.roboart.repository.event;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import ru.amm.roboart.model.event.Event;
import rx.Observable;

public interface EventApi {

    @GET("event/list")
    Observable<List<Event>> getEvents(@Header("X-PRIVATE-KEY") String token);
}
