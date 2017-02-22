package ru.amm.roboart.ui.organizers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.presenter.MvpPresenter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.amm.roboart.R;
import ru.amm.roboart.model.organizers.Organizer;
import ru.amm.roboart.ui.base.fragment.BaseFragmentView;


public class OrganizersFragment extends BaseFragmentView {

    @Inject
    OrganizersPresenter presenter;

    private OrganizersAdapter organizersAdapter;
    private OrganizersAdapter mainPartnerAdapter;
    private OrganizersAdapter partnerAdapter;

    private ScrollView organizersConteiner;
    private ProgressBar progress;

    public static OrganizersFragment newInstance() {
        return new OrganizersFragment();
    }

    @Override
    public String getName() {
        return OrganizersFragment.class.getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organizers, container, false);

        organizersConteiner = (ScrollView) view.findViewById(R.id.organizers_container);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        RecyclerView organizersList = (RecyclerView) view.findViewById(R.id.organizers_list);
        organizersList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        organizersAdapter = new OrganizersAdapter(getContext());
        organizersList.setAdapter(organizersAdapter);

        RecyclerView mainPartnersList = (RecyclerView) view.findViewById(R.id.main_partners_list);
        mainPartnersList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mainPartnerAdapter = new OrganizersAdapter(getContext());
        mainPartnersList.setAdapter(mainPartnerAdapter);

        RecyclerView partnersList = (RecyclerView) view.findViewById(R.id.partners_list);
        partnersList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        partnerAdapter = new OrganizersAdapter(getContext());
        partnersList.setAdapter(partnerAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, boolean viewRecreated) {
        super.onActivityCreated(savedInstanceState, viewRecreated);

        try {
            presenter.getOrganizers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void onSuccessContentLoaded(List<Organizer> organizersList) {
        List<Organizer> organizers = new ArrayList<>();
        List<Organizer> mainPartners = new ArrayList<>();
        List<Organizer> partners = new ArrayList<>();

        for (Organizer organizer : organizersList) {
            if (organizer.getCategory().equals("organizers")) {
                organizers.add(organizer);
            } else if (organizer.getCategory().equals("general_partners")) {
                mainPartners.add(organizer);
            } else {
                partners.add(organizer);
            }
        }

        progress.setVisibility(View.GONE);
        organizersConteiner.setVisibility(View.VISIBLE);

        organizersAdapter.clear();
        organizersAdapter.addAll(organizers);

        mainPartnerAdapter.clear();
        mainPartnerAdapter.addAll(mainPartners);

        partnerAdapter.clear();
        partnerAdapter.addAll(partners);
    }

    public void onErrorContentLoaded() {
        progress.setVisibility(View.GONE);
    }
}
