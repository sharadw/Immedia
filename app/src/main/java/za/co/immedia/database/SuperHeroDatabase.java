package za.co.immedia.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import za.co.immedia.utils.AppConstants;

@Database(entities = {SuperHeroDBModel.class}, version = 1)
public abstract class SuperHeroDatabase extends RoomDatabase {

    private static SuperHeroDatabase instance;

    public abstract SuperHeroDao personDao();

    public static synchronized SuperHeroDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                     SuperHeroDatabase.class, AppConstants.TABLE_SUPER_HERO)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
