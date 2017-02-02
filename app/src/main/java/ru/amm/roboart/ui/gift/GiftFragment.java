package ru.amm.roboart.ui.gift;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.amm.roboart.R;

public class GiftFragment extends Fragment {

    public static GiftFragment newInstance() {
        return new GiftFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift, container, false);

        Button openScheduler = (Button) view.findViewById(R.id.fragment_gift_open_scheduler);
        openScheduler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO move to scheduler screen
            }
        });
        return view;
    }
}
