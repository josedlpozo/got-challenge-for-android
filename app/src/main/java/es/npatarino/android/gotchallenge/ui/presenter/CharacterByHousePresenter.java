package es.npatarino.android.gotchallenge.ui.presenter;

import android.widget.ImageView;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.usecase.GetCharactersByHouseId;

/**
 * Created by josedelpozo on 2/5/16.
 */
public class CharacterByHousePresenter extends Presenter<CharacterListPresenter.View>
        implements CharacterPresenter{

    private GetCharactersByHouseId getCharactersByHouseId;

    String houseId;

    public CharacterByHousePresenter(GetCharactersByHouseId getCharactersByHouseId){
        this.getCharactersByHouseId = getCharactersByHouseId;
    }

    @Override
    public void initialize(){
        getCharactersByHouseId.getCharactersByHouseId(houseId, new GetCharactersByHouseId.Callback() {
            @Override
            public void charactersLoaded(List<GoTCharacter> characterList) {
                if(characterList.size() == 0){
                    getView().hideLoading();
                    getView().showEmptyCase();
                }else{
                    getView().hideLoading();
                    getView().showCharacters(characterList);
                }
            }

            @Override
            public void onError() {
                getView().showError();
            }
        });
    }

    public void setHouseId(String houseId){
        this.houseId = houseId;
    }

    @Override
    public void clickOnCharacter(GoTCharacter character, ImageView imageView) {
        getView().clickOnCharacter(character, imageView);
    }
}
