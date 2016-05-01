package es.npatarino.android.gotchallenge.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTHouse;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class GoTHouseViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "GoTHouseViewHolder";
    ImageView imp;

    public GoTHouseViewHolder(View itemView) {
        super(itemView);
        imp = (ImageView) itemView.findViewById(R.id.ivBackground);
    }

    public void render(final GoTHouse goTHouse) {
        Picasso.with(itemView.getContext()).load(goTHouse.getHouseImageUrl()).into(imp);
    }
}
