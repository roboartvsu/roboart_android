package ru.amm.roboart.ui.base;

import ru.amm.roboart.ui.base.proxy.proxy.ActivityResultProxy;
import ru.amm.roboart.ui.base.proxy.proxy.NewIntentProxy;
import ru.amm.roboart.ui.base.proxy.proxy.RequestPermissionProxy;

/**
 * интерфейс для объектов, поддерживающих проксирование событий onActivityResult, onNewIntent, onRequestPermissionsResult
 */
public interface SupportProxies {

    /**
     * регистрирует прокси на события onActivityResult
     *
     * @param activityResultProxy
     */
    void registerActivityResultProxy(ActivityResultProxy activityResultProxy);

    /**
     * регистрирует прокси на события onNewIntent
     *
     * @param newIntentProxy
     */
    void registerNewIntentProxy(NewIntentProxy newIntentProxy);

    /**
     * регистрирует прокси на события onRequestPermissionsResult
     *
     * @param requestPermissionProxy
     */
    void registerRequestPermissionProxy(RequestPermissionProxy requestPermissionProxy);
}
