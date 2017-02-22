package ru.amm.roboart.interactor;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Module;
import dagger.Provides;
import ru.amm.roboart.interactor.events.EventsInteractor;
import ru.amm.roboart.interactor.maps.MapsInteractor;
import ru.amm.roboart.interactor.organizers.OrganizersInteractor;
import ru.amm.roboart.repository.event.EventsRepository;
import ru.amm.roboart.repository.maps.MapsRepository;
import ru.amm.roboart.repository.organizers.OrganizersRepository;

@Module
public class InteractorModule {

    @PerApplication
    @Provides
    EventsInteractor provideEventsInteractor(EventsRepository eventsRepository) {
        return new EventsInteractor(eventsRepository);
    }

    @PerApplication
    @Provides
    MapsInteractor provideMapsInteractor(MapsRepository mapsRepository) {
        return new MapsInteractor(mapsRepository);
    }

    @PerApplication
    @Provides
    OrganizersInteractor provideOrganizersInteractor(OrganizersRepository organizersRepository) {
        return new OrganizersInteractor(organizersRepository);
    }
}
