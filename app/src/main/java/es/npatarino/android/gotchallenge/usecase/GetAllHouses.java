package es.npatarino.android.gotchallenge.usecase;

import android.os.Handler;
import android.os.Looper;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.repository.HousesRepository;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class GetAllHouses {

    private HousesRepository housesRepository;

    public GetAllHouses(HousesRepository housesRepository){
        this.housesRepository = housesRepository;
    }

    public void getAllHouses(final Callback callback){
        new Thread(new Runnable() {
            @Override public void run() {
                housesRepository.getHouses(new HousesRepository.Callback() {
                    @Override
                    public void housesLoaded(final List<GoTHouse> houses) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                callback.housesLoaded(houses);
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
        void housesLoaded(List<GoTHouse> houseList);

        void onError();
    }
}
