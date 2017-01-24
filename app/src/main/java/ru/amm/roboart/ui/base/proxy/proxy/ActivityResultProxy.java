package ru.amm.roboart.ui.base.proxy.proxy;

import android.content.Intent;

/**
 * Прокси для событий onActivityResult, для получения этих событий следует зарегистрировать прокси
 * с помощью метода {@link ru.amm.roboart.ui.base.activity.BaseActivityView#registerActivityResultProxy(ActivityResultProxy)}
 */
public interface ActivityResultProxy {
    void handleIntent(int requestCode, int resultCode, Intent data);
}