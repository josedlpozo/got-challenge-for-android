package es.npatarino.android.gotchallenge.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.ui.loader.ImageLoader;
import es.npatarino.android.gotchallenge.ui.presenter.HousesPresenter;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class GoTHouseViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "GoTHouseViewHolder";

    @BindView(R.id.house_image)
    ImageView houseImageView;
    @BindView(R.id.house_name)
    TextView houseName;

    HousesPresenter presenter;
    ImageLoader imageLoader;

    public GoTHouseViewHolder(View itemView, HousesPresenter presenter, ImageLoader imageLoader) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.presenter = presenter;
        this.imageLoader = imageLoader;
    }

    public void render(final GoTHouse house) {
        imageLoader.loadImage(house.getHouseImageUrl(), houseImageView);
        houseName.setText(house.getHouseName());
    }

    public void hookListener(final GoTHouse house){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickOnHouse(house, houseImageView);
            }
        });
    }
}
