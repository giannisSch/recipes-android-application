package com.foodes.recipeapp.database.UsersDb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, exportSchema = false, version = 4)
public abstract class UsersDatabase extends RoomDatabase {

    //sigleton (= to instance mias klasis apo opoudhpote an to zhthsw tha mou fernei to idio instance)
    private static UsersDatabase instance;

    //prepei na orisw pws tha parw to instance auths ths database
    @NonNull
    public static UsersDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UsersDatabase.class, "AppUsers.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract UsersDao getUserDao();


}
