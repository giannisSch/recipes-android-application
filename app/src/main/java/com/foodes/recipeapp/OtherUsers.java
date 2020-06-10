package com.foodes.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.service.autofill.UserData;
import android.util.Log;

import com.foodes.recipeapp.database.UsersDb.User;
import com.foodes.recipeapp.database.UsersDb.UsersDatabase;
import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;
import com.foodes.recipeapp.recyclerviews.ItemClickListener;
import com.foodes.recipeapp.recyclerviews.menuAndFavorites.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class OtherUsers extends AppCompatActivity {

    UsersDatabase database;
    private int userId;
    private List<User> allUsers;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_users);

        database = UsersDatabase.getInstance(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        userId = getIntent().getIntExtra("userId", 0);
        allUsers = database.getUserDao().getAll();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.otherUsersRecyclerView);

        deleteCurrentUserFromUsersList(allUsers);

        adapter = new CustomAdapter(new ItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                if(item instanceof User){
                    List<RecipeModel> otherUserFavoritesList = ((User) item).getFavorite();
                    ArrayList<RecipeModel> otherUserFavoritesArrayList = convertListToArrayList(otherUserFavoritesList);
                    Intent intent = new Intent(OtherUsers.this, OtherUserFavorites.class);
                    Bundle parameter = new Bundle();
                    parameter.putParcelableArrayList("selectedUserFavoritesList", otherUserFavoritesArrayList);
                    parameter.putInt("userId", userId);
                    intent.putExtras(parameter);
                    startActivity(intent);
                }
            }
            @Override
            public void onFavoriteClick(RecipeModel data) {
                //do nothing
            }
            @Override
            public void onOtherUserClick(User user) {
              //do nothing
            }
        });
        recyclerView.setAdapter(adapter);

        adapter.submitList(convertToObjectList(allUsers));
    }

    private void deleteCurrentUserFromUsersList(List<User> allUsersList) {
        for (User user : allUsersList) {
            if(user.getId() == userId){
                allUsersList.remove(user);
                break;
            }
        }
    }

    private List<Object> convertToObjectList(List<User> currentUserFavorites) {
        List<Object> currentUserListInObjectForm = new ArrayList<>();
        for (User user : currentUserFavorites) {
            currentUserListInObjectForm.add(user);
        }
        return currentUserListInObjectForm;
    }

    private ArrayList<RecipeModel> convertListToArrayList(List<RecipeModel> list){
        ArrayList<RecipeModel> otherUserFavoritesArrayList = new ArrayList<>();
        otherUserFavoritesArrayList.addAll(list);
        return otherUserFavoritesArrayList;
    }

}