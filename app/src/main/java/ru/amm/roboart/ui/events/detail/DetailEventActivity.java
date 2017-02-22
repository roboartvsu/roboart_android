package ru.amm.roboart.ui.events.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.bumptech.glide.Glide;

import java.sql.SQLException;

import javax.inject.Inject;

import ru.amm.roboart.R;
import ru.amm.roboart.app.App;
import ru.amm.roboart.model.event.Event;
import ru.amm.roboart.ui.base.activity.BaseActivityView;
import ru.amm.roboart.ui.base.activity.BasePresenter;

public class DetailEventActivity extends BaseActivityView {

    private static final String EXTRA_ID_EVENT = "extra_id_event";
    private static final String EXTRA_NAME_EVENT = "extra_name_event";

    @Inject
    DetailEventPresenter presenter;

    private ImageView eventColor;
    private TextView eventName;
    private TextView eventTime;
    private TextView eventLocation;
    private ImageView eventMap;
    private TextView eventDescription;


    public static void start(Context context, long idEvent, String name) {
        Intent intent = new Intent(context, DetailEventActivity.class);
        intent.putExtra(EXTRA_ID_EVENT, idEvent);
        intent.putExtra(EXTRA_NAME_EVENT, name);
        context.startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail_event;
    }

    @Override
    protected ScreenComponent createScreenComponent() {
        return DaggerDetailEventComponent
                .builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState, boolean viewRecreated) {
        super.onCreate(savedInstanceState, viewRecreated);
        setUpToolbar();
        setUpViews();
        try {
            presenter.getEvents(getIntent().getLongExtra(EXTRA_ID_EVENT, -1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setUpToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_NAME_EVENT));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void setUpViews() {
        eventColor = (ImageView) findViewById(R.id.activity_detail_event_color);
        eventName = (TextView) findViewById(R.id.activity_detail_event_name);
        eventDescription = (TextView) findViewById(R.id.activity_detail_event_description);
        eventTime = (TextView) findViewById(R.id.activity_detail_event_time);
        eventLocation = (TextView) findViewById(R.id.activity_detail_event_location);
        eventMap = (ImageView) findViewById(R.id.activity_detail_event_map);
    }

    public void onSuccessContentLoaded(Event event) {
        if (event.getCategory() != null) {
            eventColor.setBackgroundColor(Color.parseColor(event.getCategory().getHexColor()));
        }
        eventName.setText(event.getTitle());
        eventTime.setText(event.getDisplayedTime());
        eventLocation.setText(event.getLocation());
        if (!TextUtils.isEmpty(event.getDescription())) {
            eventDescription.setText(event.getDescription());
        } else {
            eventDescription.setVisibility(View.GONE);
        }

        Glide.with(this).load(App.BASE_URL + event.getMap()).into(eventMap);
    }

    public void onErrorContentLoaded() {
    }


    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public String getName() {
        return DetailEventActivity.class.getSimpleName();
    }
}
