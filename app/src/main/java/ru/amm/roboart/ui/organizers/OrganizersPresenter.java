package ru.amm.roboart.ui.organizers;

import ru.amm.roboart.interactor.common.scheduler.SchedulersProvider;
import ru.amm.roboart.ui.base.activity.BasePresenter;
import ru.amm.roboart.ui.common.error.ErrorHandler;


public class OrganizersPresenter extends BasePresenter<OrganizersFragment> {

    public OrganizersPresenter(SchedulersProvider schedulersProvider, ErrorHandler errorHandler) {
        super(schedulersProvider, errorHandler);
    }
}
