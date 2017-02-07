package ru.amm.roboart.ui.base.fragment;

import dagger.Module;
import ru.amm.roboart.ui.common.error.ErrorHandlerModule;

@Module(includes = {
        FragmentModule.class,
        ErrorHandlerModule.class})
public class FragmentViewModule {
}
