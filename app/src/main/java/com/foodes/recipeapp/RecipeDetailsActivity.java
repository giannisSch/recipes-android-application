package com.foodes.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.foodes.recipeapp.json.nutrientsModels.IngredientModel;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.recipedetails.RecipeDetailsAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {

    private TextView totalFat , totalCrabs, totalProtein, totalCalories;
    private ImageView foodImage;
    private CollapsingToolbarLayout title;
    private Button shareButton;
    private String shareURL;
    private List<Object> ingredientsList;
    private RecipeDetailsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ingredientsList = new ArrayList<Object>();
        initAdapter();
        //Get Details from Activity
        Intent intent = getIntent();
        RecipeModel recipe = intent.getParcelableExtra("RecipeModel");

        addIngredientsToList(recipe.getIngredients());
        adapter.submitList(ingredientsList);

        totalFat = (TextView)findViewById(R.id.numberOfFat);
        totalCrabs = (TextView)findViewById(R.id.numberOfCrab);
        totalProtein = (TextView)findViewById(R.id.numberOfProtein);
        totalCalories = (TextView)findViewById(R.id.numberOfCalories);
        foodImage = (ImageView)findViewById(R.id.foodImg);

        int fat = (int) recipe.getDigest().get(0).getTotal();
        int crabs = (int) recipe.getDigest().get(1).getTotal();
        int protein = (int) recipe.getDigest().get(2).getTotal();
        int calories = (int) recipe.getCalories();
        String imageUrl = recipe.getImage();
        shareURL = recipe.getShareAs();
        shareFunctionality(shareURL);

        totalFat.setText(fat+"g");
        totalCrabs.setText(crabs+"g");
        totalProtein.setText(protein+"g");
        totalCalories.setText(calories+"");
        Picasso.get().load(imageUrl).into(foodImage);


    }

    private void addIngredientsToList(List<IngredientModel> ingredients) {
        for(int i=0; i<ingredients.size(); i++){
            ingredientsList.add(ingredients.get(i));
        }
    }

    private void initAdapter(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recipeDetailsRecyclerView);
        adapter = new RecipeDetailsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void shareFunctionality(final String shareURL){
        shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareURL);
                startActivity(Intent.createChooser(shareIntent, "Sharing using"));
            }
        });
    }
}
