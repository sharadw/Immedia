package za.co.immedia.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.immedia.R;
import za.co.immedia.model.SuperHeroModel;
import za.co.immedia.utils.AppHelper;
import za.co.immedia.utils.ProgressDialogHelper;

public class CompareFragment extends Fragment {

    @BindView(R.id.barChart)
    BarChart barChart;
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
    @BindView(R.id.tvFullNameSecond)
    TextView tvFullNameSecond;
    @BindView(R.id.tvPlaceOfBirthSecond)
    TextView tvPlaceOfBirthSecond;
    @BindView(R.id.tvPublisherSecond)
    TextView tvPublisherSecond;
    @BindView(R.id.tvGenderSecond)
    TextView tvGenderSecond;
    @BindView(R.id.tvHeightSecond)
    TextView tvHeightSecond;
    @BindView(R.id.tvWeightSecond)
    TextView tvWeightSecond;
    @BindView(R.id.tvFirstSuperHero)
    TextView tvFirstSuperHero;
    @BindView(R.id.tvSecondSuperHero)
    TextView tvSecondSuperHero;



    private static final String FIRST_DATA_MODEL = "first_data_model";
    private static final String SECOND_DATA_MODEL = "second_data_model";

    private SuperHeroModel firstSuperHeroModel,secondSuperHeroModel;
    float barWidth = 0.3f;
    float barSpace = 0f;
    float groupSpace = 0.4f;

    public static CompareFragment getInstance(SuperHeroModel firstModel,SuperHeroModel secondModel) {
        CompareFragment compareFragment = new CompareFragment();
        if (firstModel != null && secondModel != null) {
            Bundle bundle = new Bundle();
            bundle.putString(FIRST_DATA_MODEL, AppHelper.getInstance().convertObjectToString(firstModel));
            bundle.putString(SECOND_DATA_MODEL, AppHelper.getInstance().convertObjectToString(secondModel));
            compareFragment.setArguments(bundle);
        }
        return compareFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && !TextUtils.isEmpty(bundle.getString(FIRST_DATA_MODEL)))
            firstSuperHeroModel = AppHelper.getInstance().convertStringToObject(bundle.getString(FIRST_DATA_MODEL), SuperHeroModel.class);

        if (bundle != null && !TextUtils.isEmpty(bundle.getString(SECOND_DATA_MODEL)))
            secondSuperHeroModel = AppHelper.getInstance().convertStringToObject(bundle.getString(SECOND_DATA_MODEL), SuperHeroModel.class);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_compare, container, false);
        ButterKnife.bind(this, rootView);
        barChart.setDescription(null);
        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawGridBackground(false);
        createBarChartInput();
        setOtherDetails();
        return rootView;
    }

    private void createBarChartInput(){
        int groupCount = 6;

        ArrayList xVals = new ArrayList();

        xVals.add("Intelligence");
        xVals.add("Strength");
        xVals.add("Speed");
        xVals.add("Durability");
        xVals.add("Power");
        xVals.add("Combat");

        ArrayList yFirstHero = new ArrayList();
        ArrayList ySecondHero = new ArrayList();
        
        if(firstSuperHeroModel != null && secondSuperHeroModel != null){

            yFirstHero.add(new BarEntry(1, Integer.parseInt(firstSuperHeroModel.getResults().get(0).getPowerstats().getIntelligence())));
            ySecondHero.add(new BarEntry(1, Integer.parseInt(secondSuperHeroModel.getResults().get(0).getPowerstats().getIntelligence())));
            yFirstHero.add(new BarEntry(2, Integer.parseInt(firstSuperHeroModel.getResults().get(0).getPowerstats().getStrength())));
            ySecondHero.add(new BarEntry(2, Integer.parseInt(secondSuperHeroModel.getResults().get(0).getPowerstats().getStrength())));
            yFirstHero.add(new BarEntry(3, Integer.parseInt(firstSuperHeroModel.getResults().get(0).getPowerstats().getSpeed())));
            ySecondHero.add(new BarEntry(3, Integer.parseInt(secondSuperHeroModel.getResults().get(0).getPowerstats().getSpeed())));
            yFirstHero.add(new BarEntry(4, Integer.parseInt(firstSuperHeroModel.getResults().get(0).getPowerstats().getDurability())));
            ySecondHero.add(new BarEntry(4, Integer.parseInt(secondSuperHeroModel.getResults().get(0).getPowerstats().getDurability())));
            yFirstHero.add(new BarEntry(5, Integer.parseInt(firstSuperHeroModel.getResults().get(0).getPowerstats().getPower())));
            ySecondHero.add(new BarEntry(5, Integer.parseInt(secondSuperHeroModel.getResults().get(0).getPowerstats().getPower())));
            yFirstHero.add(new BarEntry(6, Integer.parseInt(firstSuperHeroModel.getResults().get(0).getPowerstats().getCombat())));
            ySecondHero.add(new BarEntry(6, Integer.parseInt(secondSuperHeroModel.getResults().get(0).getPowerstats().getCombat())));

            BarDataSet set1, set2;
            set1 = new BarDataSet(yFirstHero, firstSuperHeroModel.getResults().get(0).getName());
            set1.setColor(Color.RED);
            set1.setValueTextSize(12f);
            set2 = new BarDataSet(ySecondHero, secondSuperHeroModel.getResults().get(0).getName());
            set2.setColor(Color.BLUE);
            BarData data = new BarData(set1, set2);
            set2.setValueTextSize(12f);
            data.setValueFormatter(new LargeValueFormatter());
            barChart.setData(data);
            barChart.getBarData().setBarWidth(barWidth);
            barChart.getXAxis().setAxisMinimum(0);
            barChart.getXAxis().setAxisMaximum(0+ barChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
            barChart.groupBars(0, groupSpace, barSpace);
            barChart.getData().setHighlightEnabled(false);
            barChart.invalidate();


            Legend legend = barChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            legend.setDrawInside(true);
            legend.setYOffset(100f);
            legend.setXOffset(0f);
            legend.setYEntrySpace(0f);
            legend.setTextSize(12f);

            //X-axis
            XAxis xAxis = barChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawGridLines(false);
            xAxis.setAxisMaximum(6);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
            //Y-axis
            barChart.getAxisRight().setEnabled(false);
            YAxis leftAxis = barChart.getAxisLeft();
            leftAxis.setValueFormatter(new LargeValueFormatter());
            leftAxis.setDrawGridLines(true);
            leftAxis.setSpaceTop(35f);
            leftAxis.setAxisMinimum(0f);
        }

    }

    private void setOtherDetails(){
        if (firstSuperHeroModel != null) {
            tvFirstSuperHero.setText(firstSuperHeroModel.getResults().get(0).getName());
            tvFullName.setText(firstSuperHeroModel.getResults().get(0).getBiography().getFullName());
            tvPlaceOfBirth.setText(firstSuperHeroModel.getResults().get(0).getBiography().getPlaceOfBirth());
            tvPublisher.setText(firstSuperHeroModel.getResults().get(0).getBiography().getPublisher());
            tvGender.setText(firstSuperHeroModel.getResults().get(0).getAppearance().getGender());
            tvHeight.setText(firstSuperHeroModel.getResults().get(0).getAppearance().getHeight().get(1));
            tvWeight.setText(firstSuperHeroModel.getResults().get(0).getAppearance().getWeight().get(1));
        }

        if (secondSuperHeroModel != null) {
            tvSecondSuperHero.setText(secondSuperHeroModel.getResults().get(0).getName());
            tvFullNameSecond.setText(secondSuperHeroModel.getResults().get(0).getBiography().getFullName());
            tvPlaceOfBirthSecond.setText(secondSuperHeroModel.getResults().get(0).getBiography().getPlaceOfBirth());
            tvPublisherSecond.setText(secondSuperHeroModel.getResults().get(0).getBiography().getPublisher());
            tvGenderSecond.setText(secondSuperHeroModel.getResults().get(0).getAppearance().getGender());
            tvHeightSecond.setText(secondSuperHeroModel.getResults().get(0).getAppearance().getHeight().get(1));
            tvWeightSecond.setText(secondSuperHeroModel.getResults().get(0).getAppearance().getWeight().get(1));
        }
    }
}
