package za.co.immedia.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import za.co.immedia.BuildConfig;
import za.co.immedia.database.SuperHeroDBModel;
import za.co.immedia.model.SuperHeroModel;
import za.co.immedia.repository.APIClient;
import za.co.immedia.repository.SuperHeroAPIRepo;
import za.co.immedia.repository.SuperHeroDBRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.immedia.utils.AppConstants;

@SuppressWarnings("unchecked")
public class SuperHeroViewModel extends ViewModel {

    private Application mContext;
    private MutableLiveData<SuperHeroModel> mutableLiveData;
    private SuperHeroDatabaseViewModel superHeroDatabaseViewModel;


    public SuperHeroViewModel(Application mContext) {
        this.mContext = mContext;
        superHeroDatabaseViewModel = new SuperHeroDatabaseViewModel(mContext);

    }

    public MutableLiveData<SuperHeroModel> makeRequestById(int id) {

        mutableLiveData = new MutableLiveData<>();
        SuperHeroAPIRepo mApiClient = APIClient.getInstance().getAPIService();
        Call<SuperHeroModel> personsModel = mApiClient.searchSuperHeroById(BuildConfig.ACCESS_TOKEN, id);

        personsModel.enqueue(new Callback<SuperHeroModel>() {
            @Override
            public void onResponse(Call<SuperHeroModel> call, Response<SuperHeroModel> response) {
                if (response.isSuccessful()) {
                    SuperHeroModel model = response.body();
                    if (model != null && model.getResponse().equalsIgnoreCase(AppConstants.SUCCESS)) {
                        SuperHeroDBModel superHeroDBModel = new SuperHeroDBModel();
                        superHeroDBModel.setSuperHeroId(model.getResults().get(0).getId());
                        superHeroDBModel.setName(model.getResults().get(0).getName());
                        superHeroDBModel.setUrl(model.getResults().get(0).getImage().getUrl());
                        superHeroDatabaseViewModel.insert(superHeroDBModel);
                        mutableLiveData.setValue(model);
                    } else {
                        Toast.makeText(mContext, "Character not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuperHeroModel> call, Throwable t) {
                Toast.makeText(mContext, "Character not found", Toast.LENGTH_SHORT).show();
                Log.e("SuperHero", "" + t.getMessage());
            }
        });


        return mutableLiveData;

    }

    public MutableLiveData<SuperHeroModel> makeRequestbyName(String name) {

        mutableLiveData = new MutableLiveData<>();
        SuperHeroAPIRepo mApiClient = APIClient.getInstance().getAPIService();
        Call<SuperHeroModel> personsModel = mApiClient.searchSuperHeroByName(BuildConfig.ACCESS_TOKEN, name);

        personsModel.enqueue(new Callback<SuperHeroModel>() {
            @Override
            public void onResponse(Call<SuperHeroModel> call, Response<SuperHeroModel> response) {
                if (response.isSuccessful()) {
                    SuperHeroModel model = response.body();
                    if (model != null && model.getResponse().equalsIgnoreCase(AppConstants.SUCCESS)) {
                        SuperHeroDBModel superHeroDBModel = new SuperHeroDBModel();
                        superHeroDBModel.setSuperHeroId(model.getResults().get(0).getId());
                        superHeroDBModel.setName(model.getResults().get(0).getName());
                        superHeroDBModel.setUrl(model.getResults().get(0).getImage().getUrl());
                        superHeroDatabaseViewModel.insert(superHeroDBModel);
                        mutableLiveData.setValue(model);
                    } else {
                        mutableLiveData.setValue(null);
                        Toast.makeText(mContext, "Character not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuperHeroModel> call, Throwable t) {
                Toast.makeText(mContext, "Character not found", Toast.LENGTH_SHORT).show();
                Log.e("SuperHero", "" + t.getMessage());
            }
        });


        return mutableLiveData;

    }
}
