package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class Recipes_MenuActivity extends AppCompatActivity {

    private List<Object> recipeList;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes__menu);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        recipeList = new ArrayList<Object>();
        createListData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new CustomAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Log.i("CUSTOM", item.toString());
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.submitList(recipeList);


    }

    private void createListData() {

        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("ladi");
        ingredients.add("alati");
        Recipe recipe = new Recipe("patates", "tiganites",ingredients);
        recipeList.add(recipe);
        recipe = new Recipe("makaronia", "me kima",ingredients);
        recipeList.add(recipe);

    }
}