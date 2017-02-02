package ru.amm.roboart.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.amm.roboart.BuildConfig;
import ru.amm.roboart.R;


public class AboutFragment extends Fragment {

    public static final String DATE_PATTERN = "d MMMM yyyy";
    public static final String DEVELOPER_SITE = "http://www.amm.vsu.ru/";

    private TextView versionApp;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        versionApp = (TextView) view.findViewById(R.id.fragment_about_version_app);
        Button openDeveloperSiteBtn = (Button) view.findViewById(R.id.fragment_about_move_to_developer_site);
        openDeveloperSiteBtn.setOnClickListener(v -> openDeveloperSuite());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupVersion();
    }

    private void setupVersion() {
        String versionName = BuildConfig.VERSION_NAME;
        Date buildDate = new Date(BuildConfig.TIMESTAMP);
        String date = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(buildDate);

        String appVersion = getString(R.string.about_app_version, versionName, date);

        versionApp.setText(appVersion);
    }

    private void openDeveloperSuite() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(DEVELOPER_SITE));
        startActivity(browserIntent);
    }
}
