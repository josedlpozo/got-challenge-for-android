package es.npatarino.android.gotchallenge.ui.presenter;

import android.widget.ImageView;

import es.npatarino.android.gotchallenge.model.GoTCharacter;

/**
 * Created by josedelpozo on 2/5/16.
 */
public interface CharacterPresenter {

    void clickOnCharacter(GoTCharacter character, ImageView imageView);
}
