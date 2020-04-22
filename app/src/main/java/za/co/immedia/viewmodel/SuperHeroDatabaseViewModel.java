package za.co.immedia.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import za.co.immedia.database.SuperHeroDBModel;
import za.co.immedia.model.SuperHeroModel;
import za.co.immedia.repository.SuperHeroDBRepository;

import java.util.List;

public class SuperHeroDatabaseViewModel extends AndroidViewModel {
    private SuperHeroDBRepository repository;
    private LiveData<List<SuperHeroDBModel>> listMutableLiveData;

    public SuperHeroDatabaseViewModel(@NonNull Application application) {
        super(application);
        repository = new SuperHeroDBRepository(application);
        listMutableLiveData = repository.getAllData();
    }

    public void insert(SuperHeroDBModel personList) {
        repository.insert(personList);
    }


    public LiveData<List<SuperHeroDBModel>> getAllData() {
        return listMutableLiveData;
    }
}