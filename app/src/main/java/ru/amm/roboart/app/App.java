package ru.amm.roboart.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import ru.amm.roboart.app.dagger.AppComponent;
import ru.amm.roboart.app.dagger.AppModule;
import ru.amm.roboart.app.dagger.DaggerAppComponent;

public class App extends Application {

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
                .build();
    }

    private void initLogging() {
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            Timber.plant(new Timber.DebugTree());
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