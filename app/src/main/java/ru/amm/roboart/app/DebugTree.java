package ru.amm.roboart.app;

import timber.log.Timber;

public class DebugTree extends Timber.DebugTree {
    /**
     * Удобно для фильтрации по логам
     */
    private static final String APP_IDENTIFIER = "Roboart ";

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        super.log(priority, APP_IDENTIFIER + tag, message, t);
    }
}
