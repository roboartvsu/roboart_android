package ru.amm.roboart.ui.base.proxy.proxy;

import android.support.annotation.NonNull;

/**
 * прокси для событий onNewIntent, для получения этих событий следует зарегистрировать прокси
 * с помощью метода {@link ru.amm.roboart.ui.base.activity.BaseActivityView#registerRequestPermissionProxy(RequestPermissionProxy)}
 */
public interface RequestPermissionProxy {

    void handlePermission(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
