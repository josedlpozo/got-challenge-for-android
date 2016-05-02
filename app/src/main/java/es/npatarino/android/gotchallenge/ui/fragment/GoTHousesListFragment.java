package es.npatarino.android.gotchallenge.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.datasource.HousesDataSource;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.repository.HousesRepository;
import es.npatarino.android.gotchallenge.ui.activity.DetailActivity;
import es.npatarino.android.gotchallenge.ui.adapter.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.ui.presenter.HousesPresenter;
import es.npatarino.android.gotchallenge.usecase.GetAllHouses;

/**
 * Created by josedelpozo on 29/4/16.
 */
public class GoTHousesListFragment extends BaseFragment implements HousesPresenter.View{

    private static final String TAG = "GoTHousesListFragment";

    @BindView(R.id.recycler_view)
    RecyclerView housesRecyclerView;

    GoTHouseAdapter housesAdapter;

    HousesPresenter housesPresenter;

    public GoTHousesListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        initializeAdapter();
        initializeRecyclerView();
        initializePresenter();
        return rootView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    private void initializeAdapter(){
        housesAdapter = new GoTHouseAdapter(housesPresenter);
    }

    private void initializeRecyclerView(){
        housesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        housesRecyclerView.setHasFixedSize(true);
        housesRecyclerView.setAdapter(housesAdapter);
    }

    private void initializePresenter(){
        HousesDataSource housesDataSource = new HousesDataSource();
        HousesRepository housesRepository = new HousesRepository(housesDataSource);
        GetAllHouses getAllHouses = new GetAllHouses(housesRepository);

        housesPresenter = new HousesPresenter(getAllHouses);

        housesPresenter.setView(this);
        housesPresenter.initialize();
    }

    @Override
    public void showHouses(List<GoTHouse> houses) {
        housesAdapter.addAll(houses);
    }

    @Override
    public void clickOnHouse(GoTHouse house) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("house_id", house.getHouseId());
        getContext().startActivity(intent);
    }
}
