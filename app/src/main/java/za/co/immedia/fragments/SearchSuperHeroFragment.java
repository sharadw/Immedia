package za.co.immedia.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import za.co.immedia.R;
import za.co.immedia.adapter.RecentSearchRecyclerViewAdapter;
import za.co.immedia.database.SuperHeroDBModel;
import za.co.immedia.model.SuperHeroModel;
import za.co.immedia.utils.AppConstants;
import za.co.immedia.utils.AppHelper;
import za.co.immedia.utils.ProgressDialogHelper;
import za.co.immedia.viewmodel.SuperHeroDatabaseViewModel;
import za.co.immedia.viewmodel.SuperHeroViewModel;

public class SearchSuperHeroFragment extends Fragment {


    @BindView(R.id.btnSearch)
    AppCompatButton btnSearch;
    @BindView(R.id.etSearch)
    TextInputEditText etSearch;
    @BindView(R.id.rvRecycler)
    RecyclerView rvRecycler;


    private SuperHeroViewModel superHeroViewModel;
    private SuperHeroDatabaseViewModel superHeroDatabaseViewModel;
    private ProgressDialogHelper progressDialogHelper;

    public static SearchSuperHeroFragment getInstance() {

        return new SearchSuperHeroFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        superHeroViewModel = new SuperHeroViewModel(Objects.requireNonNull(getActivity()).getApplication());
        superHeroDatabaseViewModel = new SuperHeroDatabaseViewModel(getActivity().getApplication());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_super_hero, container, false);
        ButterKnife.bind(this, rootView);
        rvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        progressDialogHelper = new ProgressDialogHelper(getContext());
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getRecent();
    }


    @OnClick(R.id.btnSearch)
    public void onSearchButtonClick() {
        AppHelper.getInstance().hideKeyboard(getActivity());
        progressDialogHelper.show();
        if (!TextUtils.isEmpty(Objects.requireNonNull(etSearch.getText()).toString())) {
            searchYourFavouriteSuperHero(etSearch.getText().toString());
            etSearch.setText("");
        } else {
            etSearch.setError("Field can not be empty");
            progressDialogHelper.hide();
        }
    }

    private void searchYourFavouriteSuperHero(String input) {

        if (!TextUtils.isEmpty(input) && TextUtils.isDigitsOnly(input)) {
            superHeroViewModel.makeRequestById(Integer.parseInt(input)).observe(getViewLifecycleOwner(), new Observer<SuperHeroModel>() {
                @Override
                public void onChanged(SuperHeroModel superHeroModel) {
                    if (superHeroModel != null  && superHeroModel.getResponse().equalsIgnoreCase(AppConstants.SUCCESS))
                        AppHelper.getInstance().addFragment(getActivity(), SuperHeroDetailsFragment.getInstance(superHeroModel), true);

                    progressDialogHelper.hide();
                }
            });
        } else {
            superHeroViewModel.makeRequestbyName(input).observe(getViewLifecycleOwner(), new Observer<SuperHeroModel>() {
                @Override
                public void onChanged(SuperHeroModel superHeroModel) {
                    if (superHeroModel != null && superHeroModel.getResponse().equalsIgnoreCase(AppConstants.SUCCESS))
                        AppHelper.getInstance().addFragment(getActivity(), SuperHeroDetailsFragment.getInstance(superHeroModel), true);

                    progressDialogHelper.hide();
                }
            });
        }
    }

    private void getRecent() {
        superHeroDatabaseViewModel.getAllData().observe(getViewLifecycleOwner(), new Observer<List<SuperHeroDBModel>>() {
            @Override
            public void onChanged(List<SuperHeroDBModel> superHeroDBModels) {
                if (superHeroDBModels != null && superHeroDBModels.size() > 0) {
                    RecentSearchRecyclerViewAdapter recyclerViewAdapter = new RecentSearchRecyclerViewAdapter(getActivity(), superHeroDBModels, superHeroViewModel, SearchSuperHeroFragment.this);
                    rvRecycler.setAdapter(recyclerViewAdapter);
                }
            }
        });
    }
}
