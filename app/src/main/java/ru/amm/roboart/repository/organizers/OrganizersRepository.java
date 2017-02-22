package ru.amm.roboart.repository.organizers;

import javax.inject.Inject;

import retrofit2.Retrofit;
import ru.amm.roboart.model.organizers.OrganizersDao;

public class OrganizersRepository {

    private OrganizersApi organizersApi;
    private OrganizersDao organizersDao;

    @Inject
    public OrganizersRepository(Retrofit retrofit, OrganizersDao organizersDao) {
        organizersApi = retrofit.create(OrganizersApi.class);
        this.organizersDao = organizersDao;

    }

}
