package com.foodes.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.foodes.recipeapp.json.nutrientsModels.HitModel;
import com.foodes.recipeapp.json.nutrientsModels.JsonModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.menu.CustomAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipesMenuActivity extends AppCompatActivity {

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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new CustomAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Log.i("CUSTOM", item.toString());
                Intent intent = new Intent(RecipesMenuActivity.this, RecipeDetailsActivity.class);
                intent.putExtra("RecipeModel", (Parcelable) item);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String ingredient = intent.getExtras().getString("ingredient");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.edamam.com/search?q=" + ingredient + "&app_id=57e6d292&app_key=000b571f7a78bfb4fb9820a7cc3b283e";
//        String url = "https://api.edamam.com/search?q=egg&app_id=57e6d292&app_key=000b571f7a78bfb4fb9820a7cc3b283e";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonModel jsonModel = new Gson().fromJson(response, JsonModel.class);
                        if (jsonModel.isMore()){    //check if json response have data
                            addRecipesToList(jsonModel);
                            adapter.submitList(recipeList);
                        }else{
                            showToastRecipesDoNotExists();
                            goToSearchActivity();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    showToastErrorNetwork();
                }
                goToSearchActivity();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void addRecipesToList(JsonModel jsonModel){
        List<HitModel> hits =  jsonModel.getHit();
        for (HitModel hit : hits){
            recipeList.add(hit.getRecipe());
        }
    }

    private void showToastRecipesDoNotExists(){
        Context context = getApplicationContext();
        CharSequence text = "There are not available recipes with this ingredient";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void showToastErrorNetwork(){
        Context context = getApplicationContext();
        CharSequence text = "Please check your Internet connection";
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    private void goToSearchActivity(){
        Intent intent = new Intent(RecipesMenuActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}