package za.co.immedia.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import za.co.immedia.model.SuperHeroModel;
import za.co.immedia.utils.AppConstants;

import java.util.List;

@Dao
public interface SuperHeroDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SuperHeroDBModel listMutableLiveData);

    @Update
    void update(SuperHeroDBModel person);

    @Delete
    void delete(SuperHeroDBModel person);

    @Query("DELETE FROM "+ AppConstants.TABLE_SUPER_HERO)
    void deleteAllNotes();

    @Query("SELECT * FROM "+AppConstants.TABLE_SUPER_HERO)
    LiveData<List<SuperHeroDBModel>> getAllData();

}
