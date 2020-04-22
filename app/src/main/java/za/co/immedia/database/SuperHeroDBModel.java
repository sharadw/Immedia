package za.co.immedia.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import za.co.immedia.utils.AppConstants;

@Entity(tableName = AppConstants.TABLE_SUPER_HERO)
public class SuperHeroDBModel {

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "superHeroId")
        @NonNull
        public String superHeroId;
        @ColumnInfo(name = "name")
        public String name;
        @ColumnInfo(name = "url")
        public String url;



    public String getSuperHeroId() {
        return superHeroId;
    }

    public void setSuperHeroId(String superHeroId) {
        this.superHeroId = superHeroId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
