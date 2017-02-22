package ru.amm.roboart.repository;

import android.content.Context;

import com.agna.ferro.mvp.component.scope.PerApplication;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @PerApplication
    @Provides
    DbHelper provideDbHelper(Context context) {
        return OpenHelperManager.getHelper(context, DbHelper.class);
    }
}
