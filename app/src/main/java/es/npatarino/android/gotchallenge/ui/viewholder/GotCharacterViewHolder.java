package es.npatarino.android.gotchallenge.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterListPresenter;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterPresenter;

/**
 * Created by josedelpozo on 28/4/16.
 */
public class GotCharacterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.character_image)
    ImageView characterImageView;
    @BindView(R.id.character_name)
    TextView characterName;

    private CharacterPresenter presenter;

    public GotCharacterViewHolder(View itemView, CharacterPresenter presenter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.presenter = presenter;
    }

    public void render(final GoTCharacter goTCharacter) {
        Picasso.with(itemView.getContext()).load(goTCharacter.getImageUrl()).into(characterImageView);
        characterName.setText(goTCharacter.getName());
    }

    public void hookListener(final GoTCharacter goTCharacter){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickOnCharacter(goTCharacter);
            }
        });
    }
}
