package com.foodes.recipeapp.database.UsersDb;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.foodes.recipeapp.json.nutrientsModels.RecipeModel;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Users")
public class User {

    //Fields
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    
    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "favorites")
    private List<RecipeModel> favorite;

//id auto generates so Im not including in constructor


    public User(@NonNull String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.favorite = new ArrayList<>();
    }

//    public User(Parcel in) {
//        id =  in.readInt();
//        username = in.readString();
//        password = in.readString();
//    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RecipeModel> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<RecipeModel> favorite) {
        this.favorite = favorite;
    }

    public void addFavorite(RecipeModel recipeModel){ this.favorite.add(recipeModel);}

    public void removeFavorite(RecipeModel recipeModel){ this.favorite.remove(recipeModel);}

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(id);
//        dest.writeString(username);
//        dest.writeString(password);
//    }
//
//    public static final Creator<User> CREATOR = new Creator<User>() {
//        @Override
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        @Override
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };
}
