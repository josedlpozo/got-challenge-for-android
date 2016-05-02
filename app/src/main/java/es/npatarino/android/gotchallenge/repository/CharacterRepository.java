package es.npatarino.android.gotchallenge.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.datasource.CharactersDataSource;
import es.npatarino.android.gotchallenge.model.GoTCharacter;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class CharacterRepository {

    private static CharacterRepository instance = null;

    private List<GoTCharacter> characters;

    private CharactersDataSource charactersDataSource;

    protected CharacterRepository(CharactersDataSource charactersDataSource){
        this.charactersDataSource = charactersDataSource;
    }

    public static CharacterRepository getInstance(CharactersDataSource charactersDataSource) {
        if(instance == null) {
            instance = new CharacterRepository(charactersDataSource);
        }
        return instance;
    }

    public void getCharacters(final Callback callback){
        charactersDataSource.getCharacters(new CharactersDataSource.Callback() {
            @Override
            public void charactersLoaded(List<GoTCharacter> charactersList) {
                characters = new ArrayList<GoTCharacter>(charactersList);
                callback.charactersLoaded(characters);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    public void getCharactersByQuery(String query, Callback callback){
        List<GoTCharacter> aux = new ArrayList<>();
        for(GoTCharacter character : characters){
            String characterName = character.getName().toLowerCase();
            String queryLowerCase = query.toLowerCase();
            if(characterName.contains(queryLowerCase)) aux.add(character);
        }

        callback.charactersLoaded(aux);
    }

    public void getCharactersByHouseId(String houseId, Callback callback){
        List<GoTCharacter> aux = new ArrayList<>();
        Log.d("eee", houseId);
        for(GoTCharacter character : characters){
            if(character.getHouseId().equals(houseId)) aux.add(character);
        }

        callback.charactersLoaded(aux);
    }

    public interface Callback{
        void charactersLoaded(List<GoTCharacter> characters);

        void onError();
    }
}
