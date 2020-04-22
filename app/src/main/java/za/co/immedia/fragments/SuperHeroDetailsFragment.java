package za.co.immedia.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.immedia.R;
import za.co.immedia.component.SuperHeroProgressView;
import za.co.immedia.model.SuperHeroModel;
import za.co.immedia.utils.AppHelper;

public class SuperHeroDetailsFragment extends Fragment {

    private SuperHeroModel superHeroModel;
    private static final String DATA_MODEL = "data_model";

    @BindView(R.id.ivHeaderImage)
    ImageView ivHeaderImage;
    @BindView(R.id.pbIntelligence)
    SuperHeroProgressView pbIntelligence;
    @BindView(R.id.pbStrength)
    SuperHeroProgressView pbStrength;
    @BindView(R.id.pbSpeed)
    SuperHeroProgressView pbSpeed;
    @BindView(R.id.pbDurability)
    SuperHeroProgressView pbDurability;
    @BindView(R.id.pbPower)
    SuperHeroProgressView pbPower;
    @BindView(R.id.pbCombat)
    SuperHeroProgressView pbCombat;
    @BindView(R.id.tvSectionTitle)
    TextView tvSectionTitle;
    @BindView(R.id.tvFullName)
    TextView tvFullName;
    @BindView(R.id.tvPlaceOfBirth)
    TextView tvPlaceOfBirth;
    @BindView(R.id.tvPublisher)
    TextView tvPublisher;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvHeight)
    TextView tvHeight;
    @BindView(R.id.tvWeight)
    TextView tvWeight;


    public static SuperHeroDetailsFragment getInstance(SuperHeroModel model) {
        SuperHeroDetailsFragment mAddTaskFragment = new SuperHeroDetailsFragment();
        if (model != null) {
            Bundle bundle = new Bundle();
            bundle.putString(DATA_MODEL, AppHelper.getInstance().convertObjectToString(model));
            mAddTaskFragment.setArguments(bundle);
        }
        return mAddTaskFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && !TextUtils.isEmpty(bundle.getString(DATA_MODEL)))
            superHeroModel = AppHelper.getInstance().convertStringToObject(bundle.getString(DATA_MODEL), SuperHeroModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_super_hero_details, container, false);
        ButterKnife.bind(this, rootView);
        try {
            setStats();
            setOtherDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }


    private void setStats() {
        if (superHeroModel != null) {
            Glide.with(this)
                    .load(superHeroModel.getResults().get(0).getImage().getUrl())
                    .placeholder(R.drawable.thunder) //placeholder
                    .into(ivHeaderImage);

            tvSectionTitle.setText(superHeroModel.getResults().get(0).getName() + " " + getContext().getResources().getString(R.string.power_stats));
            pbIntelligence.setActiveText(getContext().getResources().getString(R.string.intelligence));
            pbIntelligence.setProgress(Integer.parseInt(superHeroModel.getResults().get(0).getPowerstats().getIntelligence()), 100);
            pbIntelligence.setInactiveActiveText(superHeroModel.getResults().get(0).getPowerstats().getIntelligence());

            pbStrength.setActiveText(getContext().getResources().getString(R.string.strength));
            pbStrength.setProgress(Integer.parseInt(superHeroModel.getResults().get(0).getPowerstats().getStrength()), 100);
            pbStrength.setInactiveActiveText(superHeroModel.getResults().get(0).getPowerstats().getStrength());

            pbSpeed.setActiveText(getContext().getResources().getString(R.string.speed));
            pbSpeed.setProgress(Integer.parseInt(superHeroModel.getResults().get(0).getPowerstats().getSpeed()), 100);
            pbSpeed.setInactiveActiveText(superHeroModel.getResults().get(0).getPowerstats().getSpeed());

            pbDurability.setActiveText(getContext().getResources().getString(R.string.durability));
            pbDurability.setProgress(Integer.parseInt(superHeroModel.getResults().get(0).getPowerstats().getDurability()), 100);
            pbDurability.setInactiveActiveText(superHeroModel.getResults().get(0).getPowerstats().getDurability());

            pbPower.setActiveText(getContext().getResources().getString(R.string.power));
            pbPower.setProgress(Integer.parseInt(superHeroModel.getResults().get(0).getPowerstats().getPower()), 100);
            pbPower.setInactiveActiveText(superHeroModel.getResults().get(0).getPowerstats().getPower());

            pbCombat.setActiveText(getContext().getResources().getString(R.string.combat));
            pbCombat.setProgress(Integer.parseInt(superHeroModel.getResults().get(0).getPowerstats().getCombat()), 100);
            pbCombat.setInactiveActiveText(superHeroModel.getResults().get(0).getPowerstats().getCombat());
        }

    }

    private void setOtherDetails() {
        if (superHeroModel != null) {
            tvFullName.setText(superHeroModel.getResults().get(0).getBiography().getFullName());
            tvPlaceOfBirth.setText(superHeroModel.getResults().get(0).getBiography().getPlaceOfBirth());
            tvPublisher.setText(superHeroModel.getResults().get(0).getBiography().getPublisher());
            tvGender.setText(superHeroModel.getResults().get(0).getAppearance().getGender());
            tvHeight.setText(superHeroModel.getResults().get(0).getAppearance().getHeight().get(1));
            tvWeight.setText(superHeroModel.getResults().get(0).getAppearance().getWeight().get(1));
        }
    }
}
