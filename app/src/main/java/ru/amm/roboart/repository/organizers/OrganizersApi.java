package ru.amm.roboart.repository.organizers;

import java.util.List;

import retrofit2.http.GET;
import ru.amm.roboart.model.organizers.Organizer;
import rx.Observable;

public interface OrganizersApi {

    @GET("/organizer/list")
    Observable<List<Organizer>> getOrganizations();
}
