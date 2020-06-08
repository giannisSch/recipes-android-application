package com.foodes.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
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
import com.foodes.recipeapp.recyclerviews.menuAndFavorites.CustomAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipesMenuActivity extends AppCompatActivity {

    private List<Object> recipeList;
    private CustomAdapter adapter;
    int userId;
    private ProgressBar progressBar;
   // User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes__menu);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        recipeList = new ArrayList<Object>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        userId = getIntent().getIntExtra("UserId",0);
       // currentUser = getIntent().getParcelableExtra("User");

        adapter = new CustomAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Log.i("CUSTOM", item.toString());
                Intent intent = new Intent(RecipesMenuActivity.this, RecipeDetailsActivity.class);
                intent.putExtra("RecipeModel", (Parcelable) item);
                intent.putExtra("UserId", userId);
    //            intent.putExtra("User", currentUser);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String ingredient = intent.getStringExtra("ingredient");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.edamam.com/search?q=" + ingredient + "&app_id=57e6d292&app_key=000b571f7a78bfb4fb9820a7cc3b283e";
//        String url = "https://api.edamam.com/search?q=egg&app_id=57e6d292&app_key=000b571f7a78bfb4fb9820a7cc3b283e";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE); // make progress bar invisible
                        JsonModel jsonModel = new Gson().fromJson(response, JsonModel.class);
                        if (jsonModel.isMore()){    //check if json response have data
                            addRecipesToList(jsonModel);
                            adapter.submitList(recipeList);
                        }else{
                            showToast("There are not available recipes with this ingredient", Toast.LENGTH_LONG);
                            goToSearchActivity();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    showToast("Please check your internet connection", Toast.LENGTH_LONG);
                }
                goToSearchActivity();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void addRecipesToList(JsonModel jsonModel){
        List<HitModel> hits =  jsonModel.getHit();
        for(int i = 0; i < hits.size(); i++){
            recipeList.add(hits.get(i).getRecipe());
        }

    }

    private void showToast(CharSequence text, int duration){
        Toast.makeText(getApplicationContext(), text, duration).show();
    }

    private void goToSearchActivity(){
        Intent intent = new Intent(RecipesMenuActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}