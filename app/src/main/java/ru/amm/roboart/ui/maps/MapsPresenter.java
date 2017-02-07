package ru.amm.roboart.ui.maps;


import com.agna.ferro.mvp.component.scope.PerScreen;

import java.util.List;

import javax.inject.Inject;

import ru.amm.roboart.entity.Maps;
import ru.amm.roboart.interactor.common.scheduler.SchedulersProvider;
import ru.amm.roboart.interactor.maps.MapsInteractor;
import ru.amm.roboart.ui.base.activity.BasePresenter;
import ru.amm.roboart.ui.common.error.ErrorHandler;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@PerScreen
public class MapsPresenter extends BasePresenter<MapsFragment> {

    private MapsInteractor mapsInteractor;

    @Inject
    public MapsPresenter(SchedulersProvider schedulersProvider, ErrorHandler errorHandler, MapsInteractor mapsInteractor) {
        super(schedulersProvider, errorHandler);
        this.mapsInteractor = mapsInteractor;
    }

    public void getMaps() {
        mapsInteractor.getMaps().subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<Maps>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Maps> mapses) {
                for (Maps mapse : mapses) {
                    Timber.w("3452 m " + mapse);
                }
            }
        });
    }
}
