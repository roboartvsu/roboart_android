package ru.amm.roboart.interactor.events;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.amm.roboart.model.event.Event;
import ru.amm.roboart.repository.event.EventsRepository;
import rx.Observable;

public class EventsInteractor {

    private EventsRepository eventsRepository;

    @Inject
    public EventsInteractor(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public Observable<List<Event>> getEvents() throws SQLException {
        return eventsRepository.getEvents();
    }
}