package ru.amm.roboart.ui.events;

import com.agna.ferro.mvp.component.scope.PerScreen;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.amm.roboart.interactor.common.scheduler.SchedulersProvider;
import ru.amm.roboart.interactor.events.EventsInteractor;
import ru.amm.roboart.model.event.Event;
import ru.amm.roboart.ui.base.activity.BasePresenter;
import ru.amm.roboart.ui.common.error.ErrorHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PerScreen
public class EventsPresenter extends BasePresenter<EventsFragment> {

    private EventsInteractor eventsInteractor;

    @Inject
    public EventsPresenter(SchedulersProvider schedulersProvider, ErrorHandler errorHandler, EventsInteractor eventsInteractor) {
        super(schedulersProvider, errorHandler);
        this.eventsInteractor = eventsInteractor;

    }

    public void getEvents() throws SQLException {
        eventsInteractor.getEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Event>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onErrorContentLoaded();
                    }

                    @Override
                    public void onNext(List<Event> events) {
                        getView().onSuccessContentLoaded(events);
                    }
                });
    }
}
