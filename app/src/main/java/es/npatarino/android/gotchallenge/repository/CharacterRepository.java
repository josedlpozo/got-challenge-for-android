package es.npatarino.android.gotchallenge.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.datasource.api.CharactersDataSource;
import es.npatarino.android.gotchallenge.datasource.cache.CacheStrategy;
import es.npatarino.android.gotchallenge.datasource.db.dao.CharacterDao;
import es.npatarino.android.gotchallenge.model.GoTCharacter;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class CharacterRepository {

    private static CharacterRepository instance = null;

    private List<GoTCharacter> characters;

    private CharactersDataSource charactersDataSource;
    private CharacterDao charactersDao;
    private CacheStrategy cacheStrategy;

    protected CharacterRepository(CharactersDataSource charactersDataSource,
                                  CharacterDao charactersDao, CacheStrategy cacheStrategy){
        this.charactersDataSource = charactersDataSource;
        this.charactersDao = charactersDao;
        this.cacheStrategy = cacheStrategy;
    }

    public static CharacterRepository getInstance(CharactersDataSource charactersDataSource,
                                                  CharacterDao charactersDao,
                                                  CacheStrategy cacheStrategy){
        if(instance == null) {
            instance = new CharacterRepository(charactersDataSource, charactersDao, cacheStrategy);
        }
        return instance;
    }

    public void getCharacters(final Callback callback){
        if(cacheStrategy.isValidData()){
            characters = new ArrayList<>(charactersDao.getAllCharactersSaved());
            callback.charactersLoaded(characters);
        }else{
            charactersDataSource.getCharacters(new CharactersDataSource.Callback() {
                @Override
                public void charactersLoaded(List<GoTCharacter> charactersList) {
                    characters = new ArrayList<GoTCharacter>(charactersList);

                    charactersDao.eraseCharactersData();
                    
                    charactersDao.saveCharacterList(charactersList);

                    cacheStrategy.saveDate();

                    callback.charactersLoaded(characters);
                }

                @Override
                public void onError() {
                    callback.onError();
                }
            });
        }
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
