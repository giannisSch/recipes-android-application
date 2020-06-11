package com.foodes.recipeapp.recyclerviews.suggestions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.foodes.recipeapp.R;
import com.foodes.recipeapp.json.nutrientsModels.DiffItemCallbackClass;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;

public class SuggestionCustomAdapter extends ListAdapter<Object, SuggestionAbstractViewHolder<Object>> {


    private ItemClickListener listener;
    public SuggestionCustomAdapter(ItemClickListener listener) {
        super(new DiffItemCallbackClass<Object>());
        this.listener = listener;
    }

    @NonNull
    @Override
    public SuggestionAbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        if (viewType == R.layout.suggestion_item) {
            return new SuggestionCustomViewHolder(view, listener);
        }else {
            return new EmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionAbstractViewHolder<Object> holder, int position) {
        holder.present(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        if (item instanceof Suggestion) {
            return R.layout.suggestion_item;
        }else {
            return R.layout.holder_empty;
        }
    }


}