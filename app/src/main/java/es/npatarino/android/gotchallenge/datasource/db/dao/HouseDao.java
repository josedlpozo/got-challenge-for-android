package es.npatarino.android.gotchallenge.datasource.db.dao;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by josedelpozo on 2/5/16.
 */
public interface HouseDao {

    void saveHouse(GoTHouse house);

    void saveHousesList(List<GoTHouse> houses);

    List<GoTHouse> getAllHousesSaved();

    void eraseHousesData();
}
