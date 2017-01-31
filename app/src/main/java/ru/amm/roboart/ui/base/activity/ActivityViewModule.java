package ru.amm.roboart.ui.base.activity;

import dagger.Module;
import ru.amm.roboart.ui.common.error.ErrorHandlerModule;

@Module(includes = {
        ActivityModule.class,
        ErrorHandlerModule.class})
public class ActivityViewModule {
}
