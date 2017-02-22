package ru.amm.roboart.app.dagger;

import android.content.Context;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Component;
import ru.amm.roboart.interactor.InteractorModule;
import ru.amm.roboart.interactor.common.network.NetworkModule;
import ru.amm.roboart.interactor.common.scheduler.SchedulerModule;
import ru.amm.roboart.interactor.common.scheduler.SchedulersProvider;
import ru.amm.roboart.interactor.events.EventsInteractor;
import ru.amm.roboart.interactor.maps.MapsInteractor;
import ru.amm.roboart.repository.RepositoryModule;
import ru.amm.roboart.repository.StorageModule;
import ru.amm.roboart.repository.event.EventsRepository;
import ru.amm.roboart.repository.maps.MapsRepository;

@PerApplication
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        SchedulerModule.class,
        RepositoryModule.class,
        InteractorModule.class,
        StorageModule.class})
public interface AppComponent {
    Context context();

    SchedulersProvider schedulerProvider();

    MapsRepository mapsRepository();

    EventsRepository eventsRepository();

    MapsInteractor mapsInteractor();

    EventsInteractor eventsInteractor();
}