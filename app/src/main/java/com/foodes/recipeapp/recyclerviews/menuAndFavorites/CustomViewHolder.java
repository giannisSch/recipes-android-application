package com.foodes.recipeapp.recyclerviews.menuAndFavorites;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.foodes.recipeapp.R;
import com.foodes.recipeapp.database.UsersDb.Favorites;
import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.squareup.picasso.Picasso;

public class CustomViewHolder extends AbstractViewHolder<Object> {
    private ImageView recipeImage;
    private TextView titleText;
    private TextView dietLabelText;
    private TextView caloriesText;

    public CustomViewHolder(@NonNull View itemView, ItemClickListener listener) {
        super(itemView);
        setListener(listener);
        recipeImage = itemView.findViewById(R.id.recipeImageView);
        titleText = itemView.findViewById(R.id.titleTextView);
        dietLabelText = itemView.findViewById(R.id.dietLabelTextView);
        caloriesText = itemView.findViewById(R.id.caloriesTextView);
    }


    @Override
    public void present(Object data) {
        setData(data);
        if (data instanceof RecipeModel) {
            Picasso.get().load(((RecipeModel) data).getImage()).into(recipeImage);
            titleText.setText(((RecipeModel) data).getLabel());
            int sizeOfDietLabels = ((RecipeModel) data).getDietLabels().size();
            try{
            //    dietLabelText.append(((RecipeModel) data).getDietLabels().get(0));
            }catch (Exception e){ }
            caloriesText.setText("Calories: "+(int)(((RecipeModel) data).getCalories()));
        } else if(data instanceof User) {
           // Picasso.get().load(((Favorites) data).getFoodImage()).into(recipeImage);
            titleText.setText(((User) data).getUsername());
            dietLabelText.setText("Favorite Recipes: "+((User) data).getFavorite().size());
            caloriesText.setText("");
        }else{
            //Do something for better User Experience
        }
    }
}
