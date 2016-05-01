package es.npatarino.android.gotchallenge.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.ui.adapter.GoTAdapter;
import es.npatarino.android.gotchallenge.ui.adapter.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.ui.adapter.SectionsPagerAdapter;
import es.npatarino.android.gotchallenge.ui.viewholder.GotCharacterViewHolder;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.container)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    SectionsPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initializeToolbar();
        initializeFragmentPagerAdapter();
        initializeViewPager();
        initializeTabLayout();
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
