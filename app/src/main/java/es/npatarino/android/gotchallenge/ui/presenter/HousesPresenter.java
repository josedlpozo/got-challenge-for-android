package es.npatarino.android.gotchallenge.ui.presenter;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.usecase.GetAllCharacters;
import es.npatarino.android.gotchallenge.usecase.GetAllHouses;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class HousesPresenter extends Presenter<HousesPresenter.View>{

    private GetAllHouses getAllHouses;

    public HousesPresenter(GetAllHouses getAllHouses){
        this.getAllHouses = getAllHouses;
    }


    @Override
    public void initialize(){
        getAllHouses.getAllHouses(new GetAllHouses.Callback() {
            @Override
            public void housesLoaded(List<GoTHouse> houses) {
                if(houses.size() == 0){
                    getView().hideLoading();
                    getView().showEmptyCase();
                }else{
                    getView().hideLoading();
                    getView().showHouses(houses);
                }
            }

            @Override
            public void onError() {
                getView().hideLoading();
                getView().showError();
            }
        });
    }

    public interface View extends Presenter.View{
        void showHouses(List<GoTHouse> houses);
    }
}
