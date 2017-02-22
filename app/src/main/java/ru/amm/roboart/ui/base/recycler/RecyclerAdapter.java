package ru.amm.roboart.ui.base.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

abstract public class RecyclerAdapter<T> extends RecyclerView.Adapter<BindableViewHolder<T>> implements Filterable {

    public interface OnItemClickListener<T> {
        void onItemClick(View v, T item);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View v, T item, int position);
    }

    private final Object lock = new Object();

    private Context context;
    private LayoutInflater layoutInflater;

    private OnItemClickListener<T> onItemClickListener;
    private OnItemLongClickListener<T> onItemLongClickListener;

    protected List<T> items;

    private ArrayList<T> originalValues;
    private RecyclerAdapterFilter filter;

    public RecyclerAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    public RecyclerAdapter(Context context, @NonNull T[] items) {
        this(context, Arrays.asList(items));
    }

    public RecyclerAdapter(Context context, @NonNull List<T> items) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

        this.items = new ArrayList<>(items);
    }

    @Override
    public BindableViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return getItemHolder(getItemView(parent));
    }

    @Override
    public void onBindViewHolder(BindableViewHolder<T> bindableViewHolder, final int position) {
        final T bindItem = items.get(position);
        bindableViewHolder.bind(bindItem, position);

        if (onItemClickListener != null) {
            bindableViewHolder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v, bindItem));
        }

        if (onItemLongClickListener != null) {
            bindableViewHolder.itemView.setOnLongClickListener(v -> {
                onItemLongClickListener.onItemLongClick(v, bindItem, position);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public List<T> getItems() {
        return items;
    }

    protected View getItemView(ViewGroup parent) {
        return layoutInflater.inflate(itemViewResID(), parent, false);
    }

    abstract protected int itemViewResID();

    abstract protected BindableViewHolder<T> getItemHolder(View itemView);

    public Context getContext() {
        return context;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public void setOnItemClickListener(RecyclerAdapter.OnItemClickListener<T> l) {
        this.onItemClickListener = l;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> l) {
        this.onItemLongClickListener = l;
    }

    public void add(T item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(Collection<? extends T> collection) {
        synchronized (lock) {
            if (originalValues != null) {
                originalValues.addAll(collection);
            } else {
                items.addAll(collection);
            }
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(T item) {
        int position = items.indexOf(item);
        items.remove(item);
        notifyItemRemoved(position);
    }

    public void set(int position, T item) {
        items.remove(position);
        items.add(position, item);
        notifyItemChanged(position);
    }

    public void clear() {
        int size;
        synchronized (lock) {
            if (originalValues != null) {
                size = originalValues.size();
                originalValues.clear();
            } else {
                size = items.size();
                items.clear();
            }
        }
        notifyItemRangeRemoved(0, size);
    }

    protected List<T> getOriginalValues() {
        if (originalValues == null) {
            synchronized (lock) {
                originalValues = new ArrayList<>(items);
            }
        }
        return originalValues;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new RecyclerAdapterFilter();
        }
        return filter;
    }

    protected class RecyclerAdapterFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (TextUtils.isEmpty(constraint)) {
                ArrayList<T> list;
                synchronized (lock) {
                    list = new ArrayList<>(getOriginalValues());
                }
                results.values = list;
                results.count = list.size();
            } else {
                String prefixString = constraint.toString().toLowerCase();
                ArrayList<T> values;
                synchronized (lock) {
                    values = new ArrayList<>(getOriginalValues());
                }

                final int count = values.size();
                ArrayList<T> newValues = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    final T value = values.get(i);
                    if (matchesFilter(value, prefixString)) {
                        newValues.add(value);
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items = (List<T>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                items.clear();
                notifyDataSetChanged();
            }
        }

        /**
         * Defines if item should be added to filtered results
         *
         * @param item       item
         * @param constraint constraint
         * @return <b>true</b> if item should be presented in filtered results, <b>false</b> otherwise
         */
        protected boolean matchesFilter(T item, String constraint) {
            final String valueString = item.toString().toLowerCase();
            // First match against the whole, non-splitted value
            if (valueString.startsWith(constraint)) {
                return true;
            } else {
                final String[] words = valueString.split(" ");
                // Start at index 0, in case valueText starts with space(s)
                for (String word : words) {
                    if (word.startsWith(constraint)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}

