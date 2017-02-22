package ru.amm.roboart.interactor.organizers;

import javax.inject.Inject;

import ru.amm.roboart.repository.organizers.OrganizersRepository;

public class OrganizersInteractor {
    private OrganizersRepository organizersRepository;

    @Inject
    public OrganizersInteractor(OrganizersRepository organizersRepository) {
        this.organizersRepository = organizersRepository;
    }
}
