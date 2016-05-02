package es.npatarino.android.gotchallenge.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.ui.presenter.HousesPresenter;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class GoTHouseViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "GoTHouseViewHolder";
    ImageView imp;
    TextView tv;

    HousesPresenter presenter;

    public GoTHouseViewHolder(View itemView, HousesPresenter presenter) {
        super(itemView);
        imp = (ImageView) itemView.findViewById(R.id.ivBackground);
        tv = (TextView) itemView.findViewById(R.id.tv_name);
        this.presenter = presenter;
    }

    public void render(final GoTHouse house) {
        Picasso.with(itemView.getContext()).load(house.getHouseImageUrl()).into(imp);
        tv.setText(house.getHouseName());
    }

    public void hookListener(final GoTHouse house){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickOnHouse(house);
            }
        });
    }
}
