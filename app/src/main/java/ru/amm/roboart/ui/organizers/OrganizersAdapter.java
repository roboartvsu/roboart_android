package ru.amm.roboart.ui.organizers;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ru.amm.roboart.R;
import ru.amm.roboart.app.App;
import ru.amm.roboart.model.organizers.Organizer;
import ru.amm.roboart.ui.base.recycler.BindableViewHolder;
import ru.amm.roboart.ui.base.recycler.RecyclerAdapter;

public class OrganizersAdapter extends RecyclerAdapter<Organizer> {

    public OrganizersAdapter(Context context) {
        super(context);
    }

    @Override
    protected int itemViewResID() {
        return R.layout.list_item_organizers;
    }

    @Override
    protected BindableViewHolder<Organizer> getItemHolder(View itemView) {
        return new PhotoViewHolder(itemView);
    }

    public class PhotoViewHolder extends BindableViewHolder<Organizer> {

        private ImageView photo;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo_organizer);
        }

        @Override
        public void bind(Organizer bindItem, int position) {
            Glide.with(getContext()).load(App.BASE_URL + bindItem.getIcon()).into(photo);
        }
    }
}
