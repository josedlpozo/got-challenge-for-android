package es.npatarino.android.gotchallenge.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.ui.viewholder.GotCharacterViewHolder;

/**
 * Created by josedelpozo on 29/4/16.
 */
public class GoTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<GoTCharacter> gcs;
    private Activity a;

    public GoTAdapter(Activity activity) {
        this.gcs = new ArrayList<>();
        a = activity;
    }

    public void addAll(Collection<GoTCharacter> collection) {
        for (int i = 0; i < collection.size(); i++) {
            gcs.add((GoTCharacter) collection.toArray()[i]);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotCharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.got_character_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GotCharacterViewHolder gotCharacterViewHolder = (GotCharacterViewHolder) holder;
        gotCharacterViewHolder.render(gcs.get(position));
            /*((GotCharacterViewHolder) holder).imp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    Intent intent = new Intent(((GotCharacterViewHolder) holder).itemView.getContext(), DetailActivity.class);
                    intent.putExtra("description", gcs.get(position).getDescription());
                    intent.putExtra("name", gcs.get(position).getName());
                    intent.putExtra("imageUrl", gcs.get(position).getImageUrl());
                    ((GotCharacterViewHolder) holder).itemView.getContext().startActivity(intent);
                }
            });*/
    }

    @Override
    public int getItemCount() {
        return gcs.size();
    }


}
