package ru.amm.roboart.repository;


import com.agna.ferro.mvp.component.scope.PerApplication;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.amm.roboart.model.event.Event;
import ru.amm.roboart.model.event.EventDao;
import ru.amm.roboart.model.organizers.Organizer;
import ru.amm.roboart.model.organizers.OrganizersDao;
import ru.amm.roboart.repository.event.EventsRepository;
import ru.amm.roboart.repository.maps.MapsRepository;
import ru.amm.roboart.repository.organizers.OrganizersRepository;
import timber.log.Timber;

@Module
public class RepositoryModule {

    @Provides
    @PerApplication
    MapsRepository provideMapsRepository(Retrofit retrofit, DbHelper dbHelper) {
        return new MapsRepository(retrofit, dbHelper);
    }

    @Provides
    @PerApplication
    EventsRepository provideEventsRepository(Retrofit retrofit, EventDao eventDao) {
        return new EventsRepository(retrofit, eventDao);
    }

    @Provides
    @PerApplication
    OrganizersRepository provideOrganizersRepository(Retrofit retrofit, OrganizersDao organizersDao) {
        return new OrganizersRepository(retrofit, organizersDao);
    }

    @PerApplication
    @Provides
    EventDao provideEventDao(DbHelper dbHelper) {
        try {
            return DaoManager.createDao(dbHelper.getConnectionSource(), Event.class);
        } catch (SQLException e) {
            Timber.e(e,"");
        }
        return null;
    }

    @PerApplication
    @Provides
    OrganizersDao provideOrganizersDao(DbHelper dbHelper) {
        try {
            return DaoManager.createDao(dbHelper.getConnectionSource(), Organizer.class);
        } catch (SQLException e) {
            Timber.e(e,"");
        }
        return null;
    }
}
