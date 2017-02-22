package ru.amm.roboart.ui.organizers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.presenter.MvpPresenter;

import javax.inject.Inject;

import ru.amm.roboart.R;
import ru.amm.roboart.ui.base.fragment.BaseFragmentView;


public class OrganizersFragment extends BaseFragmentView {

    @Inject
    OrganizersPresenter presenter;

    public static OrganizersFragment newInstance() {
        return new OrganizersFragment();
    }

    @Override
    public String getName() {
        return "";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organizers, container, false);

        return view;
    }

    @Override
    protected ScreenComponent createScreenComponent() {
        return DaggerOrganizationComponent
                .builder()
                .appComponent(getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public MvpPresenter getPresenter() {
        return presenter;
    }
}
