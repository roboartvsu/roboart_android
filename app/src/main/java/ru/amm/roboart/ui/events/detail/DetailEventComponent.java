package ru.amm.roboart.ui.events.detail;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.component.scope.PerScreen;

import dagger.Component;
import ru.amm.roboart.app.dagger.AppComponent;
import ru.amm.roboart.ui.base.activity.ActivityViewModule;


@PerScreen
@Component(dependencies = AppComponent.class, modules = ActivityViewModule.class)
public interface DetailEventComponent extends ScreenComponent<DetailEventActivity> {
}
