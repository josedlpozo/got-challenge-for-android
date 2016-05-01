package es.npatarino.android.gotchallenge.usecase;

import android.os.Handler;
import android.os.Looper;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.repository.CharacterRepository;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class GetAllCharacters {

    private CharacterRepository characterRepository;

    public GetAllCharacters(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    public void getAllCharacters(final Callback callback){
        new Thread(new Runnable() {
            @Override public void run() {
                characterRepository.getCharacters(new CharacterRepository.Callback() {
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
        }).start();
    }

    public interface Callback{
        void charactersLoaded(List<GoTCharacter> characters);

        void onError();
    }
}
