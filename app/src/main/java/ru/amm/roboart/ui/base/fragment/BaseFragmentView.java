package ru.amm.roboart.ui.base.fragment;

import android.os.Handler;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.view.fragment.MvpFragmentV4View;

import ru.amm.roboart.app.App;
import ru.amm.roboart.app.dagger.AppComponent;
import ru.amm.roboart.ui.base.SupportProxies;
import ru.amm.roboart.ui.base.proxy.proxy.ActivityResultProxy;
import ru.amm.roboart.ui.base.proxy.proxy.NewIntentProxy;
import ru.amm.roboart.ui.base.proxy.proxy.RequestPermissionProxy;


/**
 * базовый класс для View, основанной на Fragment
 */
public abstract class BaseFragmentView extends MvpFragmentV4View implements SupportProxies {

    private static final int DEFAULT_DELAY = 50; //ms
    private Handler handler = new Handler();


    /**
     * Выполняет действие в главном потоке после истечения указанной задержки
     */
    public void runDelayed(Runnable runnable, int delayMs){
        handler.postDelayed(runnable, delayMs);
    }

    /**
     * То же, что и {@link #runDelayed(Runnable, int)}, только с задержкой по умолчанию
     */
    public void runDelayed(Runnable runnable){
        runDelayed(runnable, DEFAULT_DELAY);
    }

    /**
     * @return компонент экрана
     */
    public ScreenComponent getScreenComponent() {
        return getPersistentScreenScope().getObject(ScreenComponent.class);
    }

    public FragmentModule getFragmentModule() {
        return new FragmentModule(getPersistentScreenScope());
    }

    public AppComponent getAppComponent() {
        return ((App) getActivity().getApplication()).getAppComponent();
    }

    /**
     * регистрирует прокси на события onActivityResult
     */
    @Override
    public void registerActivityResultProxy(ActivityResultProxy activityResultProxy) {
        ((SupportProxies) getActivity()).registerActivityResultProxy(activityResultProxy);
    }

    /**
     * регистрирует прокси на события onNewIntent
     */
    @Override
    public void registerNewIntentProxy(NewIntentProxy newIntentProxy) {
        ((SupportProxies) getActivity()).registerNewIntentProxy(newIntentProxy);
    }

    /**
     * регистрирует прокси на события onRequestPermissionsResult
     */
    @Override
    public void registerRequestPermissionProxy(RequestPermissionProxy requestPermissionProxy) {
        ((SupportProxies) getActivity()).registerRequestPermissionProxy(requestPermissionProxy);
    }
}
