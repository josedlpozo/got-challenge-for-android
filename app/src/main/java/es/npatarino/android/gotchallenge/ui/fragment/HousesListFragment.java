package es.npatarino.android.gotchallenge.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.datasource.api.HousesDataSource;
import es.npatarino.android.gotchallenge.datasource.cache.CacheStrategy;
import es.npatarino.android.gotchallenge.datasource.db.HousesDaoImpl;
import es.npatarino.android.gotchallenge.datasource.db.dao.HouseDao;
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
        HouseDao houseDao = new HousesDaoImpl(getContext());
        CacheStrategy cacheStrategy = new CacheStrategy(getContext());
        HousesRepository housesRepository = HousesRepository.getInstance(housesDataSource,
                houseDao, cacheStrategy);
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
    public void clickOnHouse(GoTHouse house, ImageView houseImageView) {
        Intent intent = new Intent(getContext(), CharactersByHouseActivity.class);
        intent.putExtra(getString(R.string.house_id_extra), house.getHouseId());
        intent.putExtra(getString(R.string.house_name_extra), house.getHouseName());
        intent.putExtra(getString(R.string.house_image_url_extra), house.getHouseImageUrl());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(getActivity(), houseImageView, getString(R.string.activity_image_trans));
            startActivity(intent, options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }
}
