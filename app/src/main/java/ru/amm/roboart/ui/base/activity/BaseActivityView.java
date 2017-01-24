package ru.amm.roboart.ui.base.activity;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.view.activity.MvpActivityView;

import java.util.HashSet;
import java.util.Set;

import ru.amm.roboart.app.App;
import ru.amm.roboart.app.dagger.AppComponent;
import ru.amm.roboart.ui.base.SupportProxies;
import ru.amm.roboart.ui.base.proxy.proxy.ActivityResultProxy;
import ru.amm.roboart.ui.base.proxy.proxy.NewIntentProxy;
import ru.amm.roboart.ui.base.proxy.proxy.RequestPermissionProxy;

/**
 * Базовый класс для View основанной на Activity
 */
public abstract class BaseActivityView extends MvpActivityView implements SupportProxies {

    private final Set<ActivityResultProxy> activityResultProxies = new HashSet<>();
    private final Set<NewIntentProxy> newIntentProxies = new HashSet<>();
    private final Set<RequestPermissionProxy> requestPermissionProxies = new HashSet<>();

    public abstract BasePresenter getPresenter();

    public AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    @Override
    protected final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (ActivityResultProxy activityResultProxy : activityResultProxies) {
            activityResultProxy.handleIntent(requestCode, resultCode, data);
        }
    }

    @Override
    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (NewIntentProxy newIntentProxy : newIntentProxies) {
            newIntentProxy.handleIntent(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (RequestPermissionProxy requestPermissionProxy : requestPermissionProxies) {
            requestPermissionProxy.handlePermission(requestCode, permissions, grantResults);
        }
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(getPersistentScreenScope());
    }

    /**
     * @return компонент экрана
     */
    public ScreenComponent getScreenComponent() {
        return getPersistentScreenScope().getObject(ScreenComponent.class);
    }

    /**
     * регистрирует прокси на события onActivityResult
     */
    @Override
    public void registerActivityResultProxy(ActivityResultProxy activityResultProxy) {
        this.activityResultProxies.add(activityResultProxy);
    }

    /**
     * регистрирует прокси на события onNewIntent
     */
    @Override
    public void registerNewIntentProxy(NewIntentProxy newIntentProxy) {
        this.newIntentProxies.add(newIntentProxy);
    }

    /**
     * регистрирует прокси на события onRequestPermissionsResult
     */
    @Override
    public void registerRequestPermissionProxy(RequestPermissionProxy requestPermissionProxy) {
        this.requestPermissionProxies.add(requestPermissionProxy);
    }
}
