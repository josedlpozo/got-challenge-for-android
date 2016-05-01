package es.npatarino.android.gotchallenge.datasource;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by josedelpozo on 1/5/16.
 */
public interface HousesService {

    @GET("/characters")
    Call<List<GoTCharacter>> getCharacters();
}
