package ru.amm.roboart.ui.events.detail;

import java.sql.SQLException;

import javax.inject.Inject;

import ru.amm.roboart.interactor.common.scheduler.SchedulersProvider;
import ru.amm.roboart.interactor.events.EventsInteractor;
import ru.amm.roboart.model.event.Event;
import ru.amm.roboart.ui.base.activity.BasePresenter;
import ru.amm.roboart.ui.common.error.ErrorHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dmitriy on 22.02.17.
 */

public class DetailEventPresenter extends BasePresenter<DetailEventActivity> {

    private EventsInteractor eventsInteractor;

    @Inject
    public DetailEventPresenter(SchedulersProvider schedulersProvider, ErrorHandler errorHandler, EventsInteractor eventsInteractor) {
        super(schedulersProvider, errorHandler);
        this.eventsInteractor = eventsInteractor;

    }

    public void getEvents(long id) throws SQLException {
        eventsInteractor.getEventForId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Event>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onErrorContentLoaded();
                    }

                    @Override
                    public void onNext(Event events) {
                        getView().onSuccessContentLoaded(events);
                    }
                });
    }
}