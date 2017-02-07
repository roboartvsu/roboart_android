package ru.amm.roboart.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import ru.amm.roboart.BuildConfig;
import io.objectbox.BoxStore;
import ru.amm.roboart.BuildConfig;
import ru.amm.roboart.app.dagger.AppComponent;
import ru.amm.roboart.app.dagger.AppModule;
import ru.amm.roboart.app.dagger.DaggerAppComponent;
import timber.log.Timber;
import ru.amm.roboart.interactor.common.scheduler.category.MyObjectBox;
import timber.log.Timber;

public class App extends Application {

    public static final String BASE_URL = "http://private-75095-roboart.apiary-mock.com/";

    private AppComponent appComponent;

    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        boxStore = MyObjectBox.builder().androidContext(App.this).build();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initInjector();
        initLogging();
    }

    private void initInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(this,BASE_URL))
                .build();
    }

    private void initLogging() {
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            Timber.plant(new DebugTree());
        } else {
            //TODO log in crachlytics
        }
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}