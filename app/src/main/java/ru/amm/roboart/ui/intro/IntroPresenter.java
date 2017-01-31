package ru.amm.roboart.ui.intro;

import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import ru.amm.roboart.interactor.common.scheduler.SchedulersProvider;
import ru.amm.roboart.ui.base.activity.BasePresenter;
import ru.amm.roboart.ui.common.error.ErrorHandler;


@PerScreen
public class IntroPresenter extends BasePresenter<IntroActivity> {

    @Inject
    public IntroPresenter(SchedulersProvider schedulersProvider, ErrorHandler errorHandler) {
        super(schedulersProvider, errorHandler);
    }
}
