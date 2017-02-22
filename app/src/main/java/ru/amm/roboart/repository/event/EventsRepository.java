package ru.amm.roboart.repository.event;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import ru.amm.roboart.model.event.Event;
import ru.amm.roboart.model.event.EventDao;
import rx.Observable;

public class EventsRepository {

    private EventApi eventsApi;
    private EventDao eventDao;

    @Inject
    public EventsRepository(Retrofit retrofit, EventDao eventDao) {
        eventsApi = retrofit.create(EventApi.class);
        this.eventDao = eventDao;

    }

    private Observable<List<Event>> getEventsFromApi() {
        return eventsApi.getEvents("d2b13b01-ffe1-43bb-854c-e024f3840e8d").doOnNext(events -> {
            try {
                eventDao.create(events);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public Observable<List<Event>> getEvents() throws SQLException {
        List<Event> mapsList = eventDao.queryForAll();
        if (mapsList.size() != 0) {
            return Observable.just(mapsList);
        } else {
            return getEventsFromApi();
        }
    }

    public Observable<Event> getEventForId(long id) throws SQLException {
        return Observable.fromCallable(() -> eventDao.queryForId(id));
    }

}
