package za.co.immedia.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import za.co.immedia.database.SuperHeroDao;
import za.co.immedia.database.SuperHeroDBModel;
import za.co.immedia.database.SuperHeroDatabase;

import java.util.List;

public class SuperHeroDBRepository {

    private SuperHeroDao personDao;
    private LiveData<List<SuperHeroDBModel>> listMutableLiveData;

    public SuperHeroDBRepository(Application application){
        SuperHeroDatabase database = SuperHeroDatabase.getInstance(application);
        personDao = database.personDao();
        listMutableLiveData = personDao.getAllData();
    }

    public void insert(SuperHeroDBModel listMutableLiveData){

        new InsertDataAsyncTask(personDao).execute(listMutableLiveData);

    }

    public LiveData<List<SuperHeroDBModel>> getAllData(){

        return listMutableLiveData;

    }


    private static class InsertDataAsyncTask extends AsyncTask<SuperHeroDBModel,Void,Void>{

        private SuperHeroDao mPersonDao;

        private InsertDataAsyncTask(SuperHeroDao mPersonDao){
            this.mPersonDao = mPersonDao;
        }



        @Override
        protected Void doInBackground(SuperHeroDBModel... mutableLiveData) {
            mPersonDao.insert(mutableLiveData[0]);
            return null;
        }
    }


}
