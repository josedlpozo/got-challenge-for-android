package es.npatarino.android.gotchallenge.ui.presenter;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.usecase.GetAllCharacters;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class CharacterPresenter extends Presenter<CharacterPresenter.View>{

    private GetAllCharacters getAllCharacters;

    public CharacterPresenter(GetAllCharacters getAllCharacters){
        this.getAllCharacters = getAllCharacters;
    }


    @Override
    public void initialize(){
        getAllCharacters.getAllCharacters(new GetAllCharacters.Callback() {
            @Override
            public void charactersLoaded(List<GoTCharacter> characters) {
                getView().hideLoading();
                getView().showCharacters(characters);
            }

            @Override
            public void onError() {
                getView().hideLoading();
                getView().showError();
            }
        });
    }

    public interface View extends Presenter.View{
        void showCharacters(List<GoTCharacter> characters);
    }
}
