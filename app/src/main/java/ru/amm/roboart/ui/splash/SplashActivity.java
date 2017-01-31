package ru.amm.roboart.ui.splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.agna.ferro.mvp.component.ScreenComponent;

import javax.inject.Inject;

import ru.amm.roboart.MainActivity;
import ru.amm.roboart.R;
import ru.amm.roboart.ui.base.activity.BaseActivityView;
import ru.amm.roboart.ui.base.activity.BasePresenter;

public class SplashActivity extends BaseActivityView {

    private static final Integer SPLASH_DISPLAY_LENGTH = 2000;

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState, boolean viewRecreated) {
        super.onCreate(savedInstanceState, viewRecreated);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public String getName() {
        return SplashActivity.class.getSimpleName();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected ScreenComponent createScreenComponent() {
        return DaggerIntroComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }
}
