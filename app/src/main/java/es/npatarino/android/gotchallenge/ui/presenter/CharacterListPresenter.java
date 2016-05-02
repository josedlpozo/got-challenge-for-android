package es.npatarino.android.gotchallenge.ui.presenter;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.usecase.GetAllCharacters;
import es.npatarino.android.gotchallenge.usecase.GetCharactersByQuery;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class CharacterListPresenter extends Presenter<CharacterListPresenter.View>
        implements CharacterPresenter{

    private GetAllCharacters getAllCharacters;

    private GetCharactersByQuery getCharactersByQuery;

    public CharacterListPresenter(GetAllCharacters getAllCharacters,
                                  GetCharactersByQuery getCharactersByQuery){
        this.getAllCharacters = getAllCharacters;
        this.getCharactersByQuery = getCharactersByQuery;
    }


    @Override
    public void initialize(){
        getAllCharacters.getAllCharacters(new GetAllCharacters.Callback() {
            @Override
            public void charactersLoaded(List<GoTCharacter> characters) {
                if(characters.size() == 0){
                    getView().hideLoading();
                    getView().showEmptyCase();
                }else{
                    getView().hideLoading();
                    getView().showCharacters(characters);
                }
            }

            @Override
            public void onError() {
                getView().hideLoading();
                getView().showError();
            }
        });
    }

    public void clickOnCharacter(GoTCharacter character){
        getView().clickOnCharacter(character);
    }

    public void searchCharactersByQuery(String query){
        getView().clearAllCharacters();
        getView().showLoading();

        getCharactersByQuery.getCharactersByQuery(query, new GetCharactersByQuery.Callback() {
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

    public interface View extends Presenter.View{
        void showCharacters(List<GoTCharacter> characters);

        void clickOnCharacter(GoTCharacter character);

        void clearAllCharacters();
    }
}
