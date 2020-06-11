package com.foodes.recipeapp.recyclerviews.suggestions;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.foodes.recipeapp.CircleTransform;
import com.foodes.recipeapp.R;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.squareup.picasso.Picasso;

public class SuggestionCustomViewHolder extends SuggestionAbstractViewHolder<Object> {
    private ImageView recipeImage;
    private TextView titleText;


    public SuggestionCustomViewHolder(@NonNull View itemView, ItemClickListener listener) {
        super(itemView);
        setListener(listener);
        recipeImage = itemView.findViewById(R.id.searchHolderImageView);
        titleText = itemView.findViewById(R.id.searchHolderTextView);
    }


    @Override
    public void present(Object data) {
        setData(data);
        if (data instanceof Suggestion) {
            Picasso.get().load(((Suggestion) data).getImageId()).transform(new CircleTransform()).into(recipeImage);
            titleText.setText(((Suggestion) data).getParameter());
        }
            else{
            //Do something for better User Experience
        }

    }
}
