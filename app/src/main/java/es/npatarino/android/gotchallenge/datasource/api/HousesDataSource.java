package es.npatarino.android.gotchallenge.datasource.api;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.datasource.api.service.HousesService;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class HousesDataSource {

    public void getHouses(final Callback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.18.228.107:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final HousesService service = retrofit.create(HousesService.class);

        Call<List<GoTCharacter>> call = service.getCharacters();
        retrofit2.Callback callbackRetrofit = new retrofit2.Callback<List<GoTCharacter>>() {
            @Override
            public void onResponse(Call<List<GoTCharacter>> call, Response<List<GoTCharacter>> response) {
                if(response != null && response.body() != null){
                    List<GoTCharacter> characters = response.body();
                    callback.housesLoaded(getAllHousesWithCharacters(characters));
                }
            }

            @Override
            public void onFailure(Call<List<GoTCharacter>> call, Throwable t) {
                callback.onError();
            }
        };
        call.enqueue(callbackRetrofit);
    }

    private List<GoTHouse> getAllHousesWithCharacters(List<GoTCharacter> characters) {
        List<GoTHouse> houses = new ArrayList<GoTHouse>();
        for (int i = 0; i < characters.size(); i++) {
            boolean b = false;
            for (int j = 0; j < houses.size(); j++) {
                if (houses.get(j).getHouseName().equalsIgnoreCase(characters.get(i).getHouseName())) {
                    b = true;
                }
            }
            if (!b) {
                if (characters.get(i).getHouseId() != null && !characters.get(i).getHouseId().isEmpty()) {
                    GoTHouse house = new GoTHouse();
                    house.setHouseId(characters.get(i).getHouseId());
                    house.setHouseName(characters.get(i).getHouseName());
                    house.setHouseImageUrl(characters.get(i).getHouseImageUrl());
                    houses.add(house);
                }
            }
        }
        return houses;
    }

    public interface Callback {

        void housesLoaded(List<GoTHouse> houses);

        void onError();
    }
}
