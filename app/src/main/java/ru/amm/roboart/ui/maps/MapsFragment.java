package ru.amm.roboart.ui.maps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.presenter.MvpPresenter;

import javax.inject.Inject;

import ru.amm.roboart.R;
import ru.amm.roboart.ui.base.fragment.BaseFragmentView;

public class MapsFragment extends BaseFragmentView {

    @Inject
    MapsPresenter presenter;

    public static final double VENUE_LAT = 51.656636;
    public static final double VENUE_LNG = 39.206005;
    
    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        Button buildRoadBtn = (Button) view.findViewById(R.id.fragment_maps_build_road_btn);
        buildRoadBtn.setOnClickListener(view1 -> buildRoadToVenue());

        Button opentMapsBtn = (Button) view.findViewById(R.id.fragment_maps_open_maps_btn);
        opentMapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO  вынести загрузку карт с api на сплеш
                presenter.getMaps();
            }
        });

        return view;
    }

    /**
     * Построить маршрут к месту провдения меропрития
     */
    void buildRoadToVenue() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + VENUE_LAT + "," + VENUE_LNG + "(г.Воронеж, Университетская площадь 1)"));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            getActivity().startActivity(intent);
        } else {
            //TODO snack На Вашем устройстве не найдено приложение для построения маршрута
        }
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    protected ScreenComponent createScreenComponent() {
        return DaggerMapsComponent.builder()
                .appComponent(getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public MvpPresenter getPresenter() {
        return presenter;
    }
}
