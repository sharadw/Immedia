package za.co.immedia.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import za.co.immedia.R;
import za.co.immedia.component.RoundedCornerCardView;
import za.co.immedia.database.SuperHeroDBModel;
import za.co.immedia.fragments.SearchSuperHeroFragment;
import za.co.immedia.fragments.SuperHeroDetailsFragment;
import za.co.immedia.model.SuperHeroModel;
import za.co.immedia.presenter.SuperHeroPresenter;
import za.co.immedia.utils.AppConstants;
import za.co.immedia.utils.AppHelper;
import za.co.immedia.viewmodel.SuperHeroDatabaseViewModel;
import za.co.immedia.viewmodel.SuperHeroViewModel;

public class RecentSearchRecyclerViewAdapter extends RecyclerView.Adapter<RecentSearchRecyclerViewAdapter.ViewHolder> implements SuperHeroPresenter {

    private FragmentActivity mContext;
    private final List<SuperHeroDBModel> mValues;
    private LayoutInflater mLayoutInflater;
    private SuperHeroViewModel superHeroViewModel;
    private SearchSuperHeroFragment searchSuperHeroFragment;


    public RecentSearchRecyclerViewAdapter(FragmentActivity parent, List<SuperHeroDBModel> items, SuperHeroViewModel superHeroViewModel, SearchSuperHeroFragment searchSuperHeroFragment) {
        this.mValues = items;
        this.mContext = parent;
        this.superHeroViewModel = superHeroViewModel;
        this.searchSuperHeroFragment = searchSuperHeroFragment;
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.todo_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SuperHeroDBModel superHeroModel = mValues.get(position);
        if(superHeroModel != null) {
            holder.cvRoundedCornerCardView.setTitleText(superHeroModel.getName());
            holder.cvRoundedCornerCardView.setUrlImage(superHeroModel.getUrl());


            holder.cvRoundedCornerCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    superHeroViewModel.makeRequestbyName(superHeroModel.getName()).observe(searchSuperHeroFragment.getViewLifecycleOwner(), new Observer<SuperHeroModel>() {
                        @Override
                        public void onChanged(SuperHeroModel superHeroModel) {
                            if (superHeroModel.getResponse().equalsIgnoreCase(AppConstants.SUCCESS))
                                AppHelper.getInstance().addFragment(mContext, SuperHeroDetailsFragment.getInstance(superHeroModel), true);
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void clickCard(SuperHeroViewModel personsModel) {

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedCornerCardView cvRoundedCornerCardView;

        ViewHolder(View view) {
            super(view);
            cvRoundedCornerCardView = view.findViewById(R.id.cvRoundedCornerCardView);
        }


    }

}