package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.ArrayList;

public class Recipes_MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecAdapter adapter;
    private ArrayList <Recipe> recipeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes__menu);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        recipeArrayList = new ArrayList<>();
        createListData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecAdapter(recipeArrayList, new ItemClickListener(){
            @Override
            public void onItemClick(Recipe recipe) {
                //exoume to recipe
            }
        }));

    }

    private void createListData() {

        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("ladi");
        ingredients.add("alati");
        Recipe recipe = new Recipe("patates", "tiganites",ingredients);
        recipeArrayList.add(recipe);
        recipe = new Recipe("makaronia", "me kima",ingredients);
        recipeArrayList.add(recipe);

    }
}
