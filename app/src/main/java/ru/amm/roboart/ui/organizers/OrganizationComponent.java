package ru.amm.roboart.ui.organizers;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.component.scope.PerScreen;

import dagger.Component;
import ru.amm.roboart.app.dagger.AppComponent;
import ru.amm.roboart.ui.base.fragment.FragmentViewModule;

@PerScreen
@Component(dependencies = AppComponent.class, modules = FragmentViewModule.class)
public interface OrganizationComponent extends ScreenComponent<OrganizersFragment> {
}
