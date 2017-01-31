package ru.amm.roboart.ui.intro;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.agna.ferro.mvp.component.ScreenComponent;

import javax.inject.Inject;

import ru.amm.roboart.MainActivity;
import ru.amm.roboart.R;
import ru.amm.roboart.ui.base.activity.BaseActivityView;
import ru.amm.roboart.ui.base.activity.BasePresenter;

public class IntroActivity extends BaseActivityView {

    private static final Integer SPLASH_DISPLAY_LENGTH = 2000;

    @Inject
    IntroPresenter presenter;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState, boolean viewRecreated) {
        super.onCreate(savedInstanceState, viewRecreated);
        setUpViews();

        new Handler().postDelayed(() -> {
            intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
        }, SPLASH_DISPLAY_LENGTH);

    }

    private void setUpViews() {
        Button moreInfo = (Button) findViewById(R.id.more_info_btn);

    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public String getName() {
        return IntroActivity.class.getSimpleName();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_intro;
    }

    @Override
    protected ScreenComponent createScreenComponent() {
        return DaggerIntroComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }
}
