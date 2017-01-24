package ru.amm.roboart.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import ru.amm.roboart.app.dagger.AppComponent;
import ru.amm.roboart.app.dagger.AppModule;
import ru.amm.roboart.app.dagger.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initInjector();
    }

    private void initInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}