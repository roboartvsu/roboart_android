package ru.amm.roboart.ui.base.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

abstract public class BindableViewHolder<T> extends RecyclerView.ViewHolder implements Bindable<T> {

    public BindableViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    abstract public void bind(T bindItem, int position);
}
