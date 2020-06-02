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

    @Query("SELECT * FROM Users WHERE username = :username and email = :email and password = :password")
    User getUser(String username, String email , String password);

    @Query("Select * from Users")
    List<User> getAllUsers();

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);


}
