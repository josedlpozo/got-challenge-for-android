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

/**
 * Created by josedelpozo on 28/4/16.
 */
public class GotCharacterViewHolder extends RecyclerView.ViewHolder {

    ImageView imp;
    TextView tvn;

    public GotCharacterViewHolder(View itemView) {
        super(itemView);
        imp = (ImageView) itemView.findViewById(R.id.ivBackground);
        tvn = (TextView) itemView.findViewById(R.id.tv_name);
    }

    public void render(final GoTCharacter goTCharacter) {
        Picasso.with(itemView.getContext()).load(goTCharacter.getHouseImageUrl()).into(imp);
        tvn.setText(goTCharacter.getName());
    }
}
