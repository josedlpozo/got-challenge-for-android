package es.npatarino.android.gotchallenge.ui.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.ui.presenter.HousesPresenter;
import es.npatarino.android.gotchallenge.ui.viewholder.GoTHouseViewHolder;

/**
 * Created by josedelpozo on 28/4/16.
 */
public class GoTHouseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<GoTHouse> houses;
    private HousesPresenter presenter;

    public GoTHouseAdapter(HousesPresenter presenter) {
        this.houses = new ArrayList<>();
        this.presenter = presenter;
    }

    public void addAll(Collection<GoTHouse> collection) {
        houses.clear();
        houses.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.got_house_row, parent, false);
        return new GoTHouseViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GoTHouseViewHolder gotCharacterViewHolder = (GoTHouseViewHolder) holder;
        GoTHouse house = houses.get(position);
        gotCharacterViewHolder.render(house);
        gotCharacterViewHolder.hookListener(house);
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

}