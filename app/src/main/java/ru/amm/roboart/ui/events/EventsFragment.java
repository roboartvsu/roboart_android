package ru.amm.roboart.ui.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.presenter.MvpPresenter;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import ru.amm.roboart.R;
import ru.amm.roboart.model.event.Event;
import ru.amm.roboart.ui.base.fragment.BaseFragmentView;
import ru.amm.roboart.ui.base.recycler.DividerItemDecoration;
import ru.amm.roboart.ui.base.recycler.RecyclerAdapter;
import timber.log.Timber;

public class EventsFragment extends BaseFragmentView {

    @Inject
    EventsPresenter presenter;

    private RecyclerView recyclerView;
    private ProgressBar progress;

    private EventsAdapter adapter;

    @Override
    public String getName() {
        return "";
    }

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        recyclerView = (RecyclerView) view.findViewById(R.id.events_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.dimen.base_vertical_offset));

        adapter = new EventsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<Event>() {
            @Override
            public void onItemClick(View v, Event item) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, boolean viewRecreated) {
        super.onActivityCreated(savedInstanceState, viewRecreated);

        try {
            presenter.getEvents();
        } catch (SQLException e) {
            Timber.e(e, "");
        }
    }

    @Override
    protected ScreenComponent createScreenComponent() {
        return DaggerEventsComponent
                .builder()
                .appComponent(getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public MvpPresenter getPresenter() {
        return presenter;
    }

    public void onSuccessContentLoaded(List<Event> events) {
        progress.setVisibility(View.GONE);
        adapter.clear();
        adapter.addAll(events);
    }

    public void onErrorContentLoaded() {
        progress.setVisibility(View.GONE);
    }
}
