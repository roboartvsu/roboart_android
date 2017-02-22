package ru.amm.roboart.ui.events;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.amm.roboart.R;
import ru.amm.roboart.model.event.Event;
import ru.amm.roboart.ui.base.recycler.BindableViewHolder;
import ru.amm.roboart.ui.base.recycler.RecyclerAdapter;

public class EventsAdapter extends RecyclerAdapter<Event> {

    public EventsAdapter(Context context) {
        super(context);
    }

    @Override
    protected int itemViewResID() {
        return R.layout.list_item_event_list;
    }

    @Override
    protected BindableViewHolder<Event> getItemHolder(View itemView) {
        return new EventViewHolder(itemView);
    }

    public class EventViewHolder extends BindableViewHolder<Event> {

        private TextView time;
        private TextView name;
        private TextView category;
        private ImageView color;

        public EventViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.event_time);
            name = (TextView) itemView.findViewById(R.id.event_name);
            category = (TextView) itemView.findViewById(R.id.event_category);
            color = (ImageView) itemView.findViewById(R.id.event_color);
        }

        @Override
        public void bind(Event bindItem, int position) {
            time.setText(bindItem.getDisplayedTime());
            name.setText(bindItem.getTitle());
            if (bindItem.getCategory() != null) {
                category.setText(bindItem.getCategory().getTitle());
                color.setBackgroundColor(Color.parseColor(bindItem.getCategory().getHexColor()));
            }
        }
    }
}
