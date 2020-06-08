package com.foodes.recipeapp.database.UsersDb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.foodes.recipeapp.database.UsersDb.User;

import java.util.List;

@Dao
public interface UsersDao {

    //gets everywhere
    @Query("SELECT * FROM Users")
    public List<User> getAll();

    //login
    @Query("SELECT * FROM Users WHERE id = :id")
    List<User> getUser(int id);

    @Query("SELECT * FROM Users WHERE username = :username")
    List<User> getUserNames(String username);

//    @Query("SELECT * FROM Users WHERE id = :id")
//    List<User> getFavorites(int id);



    @Insert
    public void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);


}
