package es.npatarino.android.gotchallenge.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.animation.PathInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ui.adapter.SectionsPagerAdapter;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.container)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    SectionsPagerAdapter fragmentPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initializeToolbar();
        initializeFragmentPagerAdapter();
        initializeViewPager();
        initializeTabLayout();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    private void initializeToolbar(){
        setSupportActionBar(toolbar);
    }

    private void initializeFragmentPagerAdapter(){
        fragmentPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
    }

    private void initializeViewPager(){
        viewPager.setAdapter(fragmentPagerAdapter);
    }

    private void initializeTabLayout(){
        tabLayout.setupWithViewPager(viewPager);
    }

}
