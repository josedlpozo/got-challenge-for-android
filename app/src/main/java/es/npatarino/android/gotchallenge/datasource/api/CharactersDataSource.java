package es.npatarino.android.gotchallenge.datasource.api;

import java.util.List;

import es.npatarino.android.gotchallenge.datasource.api.service.CharacterService;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class CharactersDataSource {

    public void getCharacters(final Callback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.18.228.107:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final CharacterService service = retrofit.create(CharacterService.class);

        Call<List<GoTCharacter>> call = service.getCharacters();
        retrofit2.Callback callbackRetrofit = new retrofit2.Callback<List<GoTCharacter>>() {
            @Override
            public void onResponse(Call<List<GoTCharacter>> call, Response<List<GoTCharacter>> response) {
                if(response != null && response.body() != null)
                    callback.charactersLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<GoTCharacter>> call, Throwable t) {
                callback.onError();
            }
        };
        call.enqueue(callbackRetrofit);
    }

    public interface Callback {

        void charactersLoaded(List<GoTCharacter> characters);

        void onError();
    }
}
