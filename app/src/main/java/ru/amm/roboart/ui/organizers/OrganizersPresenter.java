package ru.amm.roboart.ui.organizers;

import com.agna.ferro.mvp.component.scope.PerScreen;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.amm.roboart.interactor.common.scheduler.SchedulersProvider;
import ru.amm.roboart.interactor.organizers.OrganizersInteractor;
import ru.amm.roboart.model.organizers.Organizer;
import ru.amm.roboart.ui.base.activity.BasePresenter;
import ru.amm.roboart.ui.common.error.ErrorHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PerScreen
public class OrganizersPresenter extends BasePresenter<OrganizersFragment> {

    private OrganizersInteractor organizersInteractor;

    @Inject
    public OrganizersPresenter(SchedulersProvider schedulersProvider, ErrorHandler errorHandler, OrganizersInteractor organizersInteractor) {
        super(schedulersProvider, errorHandler);
        this.organizersInteractor = organizersInteractor;
    }

    public void getOrganizers() throws SQLException {
        organizersInteractor.getOrganizers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Organizer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onErrorContentLoaded();
                    }

                    @Override
                    public void onNext(List<Organizer> organizers) {
                        getView().onSuccessContentLoaded(organizers);
                    }
                });
    }
}
