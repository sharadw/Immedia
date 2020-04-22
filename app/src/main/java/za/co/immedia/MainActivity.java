package za.co.immedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

import za.co.immedia.fragments.SearchSuperHeroFragment;
import za.co.immedia.utils.AppHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppHelper.getInstance().addFragment(this,SearchSuperHeroFragment.getInstance(), false);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       /* if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
            AppHelper.getInstance().addFragment(this, MasterFragment.getInstance(true), false);
        }
        else {
            setContentView(R.layout.activity_main);
            AppHelper.getInstance().addFragment(this, SearchSuperHeroFragment.getInstance(), false);
        }*/
    }
}
