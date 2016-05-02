package es.npatarino.android.gotchallenge.datasource.db.dao;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;

/**
 * Created by josedelpozo on 2/5/16.
 */
public interface CharacterDao {

    void saveCharacter(GoTCharacter character);

    void saveCharacterList(List<GoTCharacter> characters);

    List<GoTCharacter> getAllCharactersSaved();

    void eraseCharactersData();
}
