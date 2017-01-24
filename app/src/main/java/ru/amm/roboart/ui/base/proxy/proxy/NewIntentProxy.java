package ru.amm.roboart.ui.base.proxy.proxy;

import android.content.Intent;

/**
 * прокси для событий onNewIntent, для получения этих событий следует зарегистрировать прокси
 * с помощью метода {@link ru.amm.roboart.ui.base.activity.BaseActivityView#registerNewIntentProxy(NewIntentProxy)}
 */
public interface NewIntentProxy {

    void handleIntent(Intent newIntent);
}
