package ru.amm.roboart.repository.organizers;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import ru.amm.roboart.model.organizers.Organizer;
import rx.Observable;

public interface OrganizersApi {

    @GET("/organizer/list")
    Observable<List<Organizer>> getOrganizations(@Header("X-PRIVATE-KEY") String token);
}
