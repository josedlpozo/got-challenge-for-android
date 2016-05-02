package es.npatarino.android.gotchallenge.usecase;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.repository.CharacterRepository;

/**
 * Created by josedelpozo on 2/5/16.
 */
public class GetCharactersByHouseId {

    private CharacterRepository characterRepository;

    public GetCharactersByHouseId(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    public void getCharactersByHouseId(final String houseId, final Callback callback){
        Log.d("eee", "getCharactersByHouseID "+houseId);
        new Thread(new Runnable() {
            @Override
            public void run() {
                characterRepository.getCharactersByHouseId(houseId, new CharacterRepository.Callback() {
                    @Override
                    public void charactersLoaded(final List<GoTCharacter> characters) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                callback.charactersLoaded(characters);
                            }
                        });
                    }

                    @Override
                    public void onError() {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onError();
                            }
                        });
                    }
                });
            }
        }).run();
    }

    public interface Callback{
        void charactersLoaded(List<GoTCharacter> characterList);

        void onError();
    }
}
