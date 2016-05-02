package es.npatarino.android.gotchallenge.datasource.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by josedelpozo on 2/5/16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "GoT.DB";

    private static final int DATABASE_VERSION = 1;

    private Dao<GoTCharacter, String> characterDao = null;

    private Dao<GoTHouse, String> houseDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, GoTCharacter.class);
            TableUtils.createTable(connectionSource, GoTHouse.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, GoTCharacter.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    public Dao<GoTCharacter, String> getCharacterDao() {
        if (null == characterDao) {
            try {
                characterDao = getDao(GoTCharacter.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return characterDao;
    }

    public Dao<GoTHouse, String> getHouseDao() {
        if (null == houseDao) {
            try {
                houseDao = getDao(GoTHouse.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return houseDao;
    }

    public void eraseCharactersData(){
        try {
            TableUtils.clearTable(getConnectionSource(), GoTCharacter.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void eraseHousesData(){
        try {
            TableUtils.clearTable(getConnectionSource(), GoTHouse.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

}