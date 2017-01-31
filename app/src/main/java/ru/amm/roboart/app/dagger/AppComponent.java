package ru.amm.roboart.app.dagger;

import android.content.Context;

import com.agna.ferro.mvp.component.scope.PerApplication;

import dagger.Component;
import ru.amm.roboart.interactor.common.network.NetworkModule;
import ru.amm.roboart.interactor.common.scheduler.SchedulerModule;

@PerApplication
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        SchedulerModule.class})
public interface AppComponent {
    Context context();
}