package es.npatarino.android.gotchallenge.repository;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.datasource.HousesDataSource;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class HousesRepository {

    private List<GoTHouse> houses;

    private HousesDataSource housesDataSource;

    public HousesRepository(HousesDataSource housesDataSource){
        this.housesDataSource = housesDataSource;
    }

    public void getHouses(final Callback callback){
        housesDataSource.getHouses(new HousesDataSource.Callback() {
            @Override
            public void housesLoaded(List<GoTHouse> housesList) {
                houses = new ArrayList<GoTHouse>(housesList);
                callback.housesLoaded(houses);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    public interface Callback{
        void housesLoaded(List<GoTHouse> houses);

        void onError();
    }
}
