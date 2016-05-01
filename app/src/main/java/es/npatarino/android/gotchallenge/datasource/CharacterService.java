package es.npatarino.android.gotchallenge.datasource;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by josedelpozo on 1/5/16.
 */
public interface CharacterService {

    @GET("/characters")
    Call<List<GoTCharacter>> getCharacters();
}
