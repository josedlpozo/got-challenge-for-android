package es.npatarino.android.gotchallenge.datasource.db;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.datasource.db.dao.CharacterDao;
import es.npatarino.android.gotchallenge.model.GoTCharacter;

/**
 * Created by josedelpozo on 2/5/16.
 */
public class CharactersDaoImpl implements CharacterDao {

    private DatabaseHelper helper;

    public CharactersDaoImpl(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public void saveCharacter(GoTCharacter character) {
        try {
            helper.getCharacterDao().create(character);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCharacterList(List<GoTCharacter> characters) {
        for(GoTCharacter character : characters) {
            try {
                helper.getCharacterDao().create(character);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<GoTCharacter> getAllCharactersSaved() {
        List<GoTCharacter> characters = new ArrayList<>();
        try {
            characters.addAll(helper.getCharacterDao().queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }

    @Override
    public void eraseCharactersData() {
        helper.eraseCharactersData();
    }
}
