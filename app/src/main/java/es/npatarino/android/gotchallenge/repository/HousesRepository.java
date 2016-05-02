package es.npatarino.android.gotchallenge.repository;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.datasource.api.HousesDataSource;
import es.npatarino.android.gotchallenge.datasource.cache.CacheStrategy;
import es.npatarino.android.gotchallenge.datasource.db.dao.HouseDao;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class HousesRepository {

    private static HousesRepository instance = null;

    private List<GoTHouse> houses;

    private HousesDataSource housesDataSource;
    private HouseDao houseDao;
    private CacheStrategy cacheStrategy;

    protected HousesRepository(HousesDataSource housesDataSource, HouseDao houseDao,
                               CacheStrategy cacheStrategy){
        this.housesDataSource = housesDataSource;
        this.houseDao = houseDao;
        this.cacheStrategy = cacheStrategy;
    }

    public static HousesRepository getInstance(HousesDataSource housesDataSource, HouseDao houseDao,
                                               CacheStrategy cacheStrategy){
        if(instance == null) {
            instance = new HousesRepository(housesDataSource, houseDao, cacheStrategy);
        }
        return instance;
    }

    public void getHouses(final Callback callback){
        if(cacheStrategy.isValidData()){
            houses = new ArrayList<>(houseDao.getAllHousesSaved());
            callback.housesLoaded(houses);
        }else {
            housesDataSource.getHouses(new HousesDataSource.Callback() {
                @Override
                public void housesLoaded(List<GoTHouse> housesList) {
                    houses = new ArrayList<GoTHouse>(housesList);

                    houseDao.eraseHousesData();

                    houseDao.saveHousesList(housesList);

                    cacheStrategy.saveDate();

                    callback.housesLoaded(houses);
                }

                @Override
                public void onError() {
                    callback.onError();
                }
            });
        }
    }

    public interface Callback{
        void housesLoaded(List<GoTHouse> houses);

        void onError();
    }
}
