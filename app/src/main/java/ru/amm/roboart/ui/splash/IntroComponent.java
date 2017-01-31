package ru.amm.roboart.ui.splash;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.component.scope.PerScreen;

import dagger.Component;
import ru.amm.roboart.app.dagger.AppComponent;
import ru.amm.roboart.ui.base.activity.ActivityViewModule;

@PerScreen
@Component(dependencies = AppComponent.class, modules = ActivityViewModule.class)
public interface IntroComponent extends ScreenComponent<SplashActivity> {
}
