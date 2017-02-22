package ru.amm.roboart.interactor.organizers;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.amm.roboart.model.organizers.Organizer;
import ru.amm.roboart.repository.organizers.OrganizersRepository;
import rx.Observable;

public class OrganizersInteractor {

    private OrganizersRepository organizersRepository;

    @Inject
    public OrganizersInteractor(OrganizersRepository organizersRepository) {
        this.organizersRepository = organizersRepository;
    }

    public Observable<List<Organizer>> getOrganizers() throws SQLException {
        return organizersRepository.getOrganizers();
    }
}
