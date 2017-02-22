package ru.amm.roboart.ui.organizers;

import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import ru.amm.roboart.interactor.common.scheduler.SchedulersProvider;
import ru.amm.roboart.interactor.organizers.OrganizersInteractor;
import ru.amm.roboart.ui.base.activity.BasePresenter;
import ru.amm.roboart.ui.common.error.ErrorHandler;

@PerScreen
public class OrganizersPresenter extends BasePresenter<OrganizersFragment> {

    private OrganizersInteractor organizersInteractor;

    @Inject
    public OrganizersPresenter(SchedulersProvider schedulersProvider, ErrorHandler errorHandler, OrganizersInteractor organizersInteractor) {
        super(schedulersProvider, errorHandler);
        this.organizersInteractor = organizersInteractor;
    }
}
