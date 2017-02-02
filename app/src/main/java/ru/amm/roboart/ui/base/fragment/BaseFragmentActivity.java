package ru.amm.roboart.ui.base.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

import ru.amm.roboart.app.App;
import ru.amm.roboart.app.dagger.AppComponent;
import ru.amm.roboart.ui.base.SupportProxies;
import ru.amm.roboart.ui.base.proxy.proxy.ActivityResultProxy;
import ru.amm.roboart.ui.base.proxy.proxy.NewIntentProxy;
import ru.amm.roboart.ui.base.proxy.proxy.RequestPermissionProxy;

/**
 * базовый класс для Activity, которая является контейнером для Fragment
 */
public class BaseFragmentActivity extends AppCompatActivity implements SupportProxies {

    private Set<ActivityResultProxy> activityResultProxies = new HashSet<>();
    private Set<NewIntentProxy> newIntentProxies = new HashSet<>();
    private Set<RequestPermissionProxy> requestPermissionProxies = new HashSet<>();


    protected AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
