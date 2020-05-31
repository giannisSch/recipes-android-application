package com.foodes.recipeapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import com.foodes.recipeapp.json.nutrientsModels.*;

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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new CustomAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                Log.i("CUSTOM", item.toString());
            }
        });
        recyclerView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.edamam.com/search?q=egg&app_id=57e6d292&app_key=000b571f7a78bfb4fb9820a7cc3b283e";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonModel jsonModel = new Gson().fromJson(response, JsonModel.class);
                        Log.i("RESPONSE", jsonModel.getQ());
                        addRecipesToList(jsonModel);
                        adapter.submitList(recipeList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //do something
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

}