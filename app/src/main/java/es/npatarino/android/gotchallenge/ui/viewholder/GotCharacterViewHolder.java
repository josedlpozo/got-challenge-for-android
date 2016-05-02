package es.npatarino.android.gotchallenge.ui.viewholder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterPresenter;

/**
 * Created by josedelpozo on 28/4/16.
 */
public class GotCharacterViewHolder extends RecyclerView.ViewHolder {

    private CharacterPresenter presenter;

    ImageView imp;
    TextView tvn;

    public GotCharacterViewHolder(View itemView, CharacterPresenter presenter) {
        super(itemView);
        this.presenter = presenter;
        imp = (ImageView) itemView.findViewById(R.id.ivBackground);
        tvn = (TextView) itemView.findViewById(R.id.tv_name);
    }

    public void render(final GoTCharacter goTCharacter) {
        Picasso.with(itemView.getContext()).load(goTCharacter.getImageUrl()).into(imp);
        tvn.setText(goTCharacter.getName());
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
