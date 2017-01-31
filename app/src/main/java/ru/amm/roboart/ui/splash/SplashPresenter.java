package ru.amm.roboart.ui.splash;

import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import ru.amm.roboart.interactor.common.scheduler.SchedulersProvider;
import ru.amm.roboart.ui.base.activity.BasePresenter;
import ru.amm.roboart.ui.common.error.ErrorHandler;


@PerScreen
public class SplashPresenter extends BasePresenter<SplashActivity> {

    @Inject
    public SplashPresenter(SchedulersProvider schedulersProvider, ErrorHandler errorHandler) {
        super(schedulersProvider, errorHandler);
    }
}
