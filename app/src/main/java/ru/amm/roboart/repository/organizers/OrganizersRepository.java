package ru.amm.roboart.repository.organizers;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import ru.amm.roboart.model.organizers.Organizer;
import ru.amm.roboart.model.organizers.OrganizersDao;
import rx.Observable;

public class OrganizersRepository {

    private OrganizersApi organizersApi;
    private OrganizersDao organizersDao;

    @Inject
    public OrganizersRepository(Retrofit retrofit, OrganizersDao organizersDao) {
        organizersApi = retrofit.create(OrganizersApi.class);
        this.organizersDao = organizersDao;

    }

    private Observable<List<Organizer>> getOrganizersFromApi() {
        return organizersApi.getOrganizations("d2b13b01-ffe1-43bb-854c-e024f3840e8d").doOnNext(events -> {
            try {
                organizersDao.create(events);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public Observable<List<Organizer>> getOrganizers() throws SQLException {
        List<Organizer> mapsList = organizersDao.queryForAll();
        if (mapsList.size() != 0) {
            return Observable.just(mapsList);
        } else {
            return getOrganizersFromApi();
        }
    }
}
