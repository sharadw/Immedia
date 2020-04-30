package za.co.immedia.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import za.co.immedia.R;
import za.co.immedia.model.SuperHeroModel;
import za.co.immedia.utils.AppConstants;
import za.co.immedia.utils.AppHelper;
import za.co.immedia.utils.ProgressDialogHelper;
import za.co.immedia.viewmodel.SuperHeroViewModel;

public class CompareDialogFragment extends DialogFragment {

    private static final String FIRST_DATA_MODEL = "first_data_model";
    @BindView(R.id.btnCompare)
    AppCompatButton btnCompare;
    @BindView(R.id.etSearch)
    TextInputEditText etSearch;

    private ProgressDialogHelper progressDialogHelper;
    private SuperHeroViewModel superHeroViewModel;
    private SuperHeroModel firstSuperHeroModel;

    public static CompareDialogFragment getInstance(SuperHeroModel model) {
        CompareDialogFragment compareDialogFragment = new CompareDialogFragment();
        if (model != null) {
            Bundle bundle = new Bundle();
            bundle.putString(FIRST_DATA_MODEL, AppHelper.getInstance().convertObjectToString(model));
            compareDialogFragment.setArguments(bundle);
        }
        return compareDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        progressDialogHelper = new ProgressDialogHelper(getContext());
        superHeroViewModel = new SuperHeroViewModel(Objects.requireNonNull(getActivity()).getApplication());

        Bundle bundle = getArguments();
        if (bundle != null && !TextUtils.isEmpty(bundle.getString(FIRST_DATA_MODEL)))
            firstSuperHeroModel = AppHelper.getInstance().convertStringToObject(bundle.getString(FIRST_DATA_MODEL), SuperHeroModel.class);

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_compare, container, false);
        ButterKnife.bind(this, rootView);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return rootView;
    }

    @OnClick(R.id.btnCompare)
    void compareHero(){
        AppHelper.getInstance().hideKeyboard(getActivity());
        progressDialogHelper.show();
        if (!TextUtils.isEmpty(Objects.requireNonNull(etSearch.getText()).toString())) {
            compareWithYourFavouriteSuperHero(etSearch.getText().toString());
            etSearch.setText("");
        } else {
            etSearch.setError("Field can not be empty");
            progressDialogHelper.hide();
        }
    }

    private void compareWithYourFavouriteSuperHero(String input) {

        if (!TextUtils.isEmpty(input) && TextUtils.isDigitsOnly(input)) {
            superHeroViewModel.makeRequestById(Integer.parseInt(input)).observe(getViewLifecycleOwner(), new Observer<SuperHeroModel>() {
                @Override
                public void onChanged(SuperHeroModel superHeroModel) {
                    if (superHeroModel != null  && superHeroModel.getResponse().equalsIgnoreCase(AppConstants.SUCCESS))
                        AppHelper.getInstance().addFragment(getActivity(), CompareFragment.getInstance(firstSuperHeroModel,superHeroModel), true);
                    CompareDialogFragment.this.dismiss();
                    progressDialogHelper.hide();
                }
            });
        } else {
            superHeroViewModel.makeRequestbyName(input).observe(getViewLifecycleOwner(), new Observer<SuperHeroModel>() {
                @Override
                public void onChanged(SuperHeroModel superHeroModel) {
                    if (superHeroModel != null && superHeroModel.getResponse().equalsIgnoreCase(AppConstants.SUCCESS))
                        AppHelper.getInstance().addFragment(getActivity(), CompareFragment.getInstance(firstSuperHeroModel,superHeroModel), true);
                    CompareDialogFragment.this.dismiss();
                    progressDialogHelper.hide();
                }
            });
        }
    }
}
