package es.npatarino.android.gotchallenge.datasource.db;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.datasource.db.dao.HouseDao;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by josedelpozo on 2/5/16.
 */
public class HousesDaoImpl implements HouseDao {

    private DatabaseHelper helper;

    public HousesDaoImpl(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public void saveHouse(GoTHouse house) {
        try {
            helper.getHouseDao().create(house);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveHousesList(List<GoTHouse> houses) {
        for(GoTHouse house : houses){
            try {
                helper.getHouseDao().create(house);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<GoTHouse> getAllHousesSaved() {
        List<GoTHouse> houses = new ArrayList<>();
        try {
            houses.addAll(helper.getHouseDao().queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houses;
    }

    @Override
    public void eraseHousesData() {
        helper.eraseHousesData();
    }
}
