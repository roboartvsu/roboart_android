package ru.amm.roboart.ui.events.detail;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import ru.amm.roboart.R;
import ru.amm.roboart.app.App;
import uk.co.senab.photoview.PhotoViewAttacher;

public class FullScreenEventMapActivity extends AppCompatActivity {

    private static final String EXTRA_EVENT_MAP = "extra_event_map";
    private static final String EXTRA_EVENT_LOCATION = "extra_event_location";
    private static final float MAX_ZOOM = 10;

    private PhotoViewAttacher attacher;
    private ImageView mapPhoto;

    public static void start(Context context, String mapUrl, String location) {
        Intent intent = new Intent(context, FullScreenEventMapActivity.class);
        intent.putExtra(EXTRA_EVENT_MAP, mapUrl);
        intent.putExtra(EXTRA_EVENT_LOCATION, location);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_event_map);
        setUpToolbar();

        mapPhoto = (ImageView) findViewById(R.id.activity_fullscreen_map);
        attacher = new PhotoViewAttacher(mapPhoto);
        attacher.setMaximumScale(MAX_ZOOM);


        String mapUrl = getIntent().getStringExtra(EXTRA_EVENT_MAP);
        if (!TextUtils.isEmpty(mapUrl)) {
            loadImage(mapUrl);
        }
    }

    private void setUpToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_EVENT_LOCATION));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void loadImage(String imageUrl) {
        Glide.with(this)
                .load(App.BASE_URL + imageUrl)

                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        mapPhoto.setImageDrawable(resource);
                        attacher.update();
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        //TODO error placeholder
                        attacher.update();
                    }
                });
    }
}
