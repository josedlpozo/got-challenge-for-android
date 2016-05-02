package es.npatarino.android.gotchallenge.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.datasource.HousesDataSource;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.repository.HousesRepository;
import es.npatarino.android.gotchallenge.ui.activity.CharactersByHouseActivity;
import es.npatarino.android.gotchallenge.ui.adapter.HousesAdapter;
import es.npatarino.android.gotchallenge.ui.presenter.HousesPresenter;
import es.npatarino.android.gotchallenge.usecase.GetAllHouses;

/**
 * Created by josedelpozo on 29/4/16.
 */
public class HousesListFragment extends BaseFragment implements HousesPresenter.View{

    private static final String TAG = "HousesListFragment";

    @BindView(R.id.recycler_view)
    RecyclerView housesRecyclerView;

    HousesAdapter housesAdapter;

    HousesPresenter housesPresenter;

    public HousesListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();

        return rootView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    private void initializeAdapter(){
        housesAdapter = new HousesAdapter(housesPresenter);
    }

    private void initializeRecyclerView(){
        housesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        housesRecyclerView.setHasFixedSize(true);
        housesRecyclerView.setAdapter(housesAdapter);
    }

    private void initializePresenter(){
        HousesDataSource housesDataSource = new HousesDataSource();
        HousesRepository housesRepository = HousesRepository.getInstance(housesDataSource);
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
        Intent intent = new Intent(getContext(), CharactersByHouseActivity.class);
        intent.putExtra(getString(R.string.house_id_extra), house.getHouseId());
        intent.putExtra(getString(R.string.house_name_extra), house.getHouseName());
        intent.putExtra(getString(R.string.house_image_url_extra), house.getHouseImageUrl());
        getContext().startActivity(intent);
    }
}
