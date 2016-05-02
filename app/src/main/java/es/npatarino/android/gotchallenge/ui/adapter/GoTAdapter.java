package es.npatarino.android.gotchallenge.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.ui.activity.DetailActivity;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterPresenter;
import es.npatarino.android.gotchallenge.ui.viewholder.GotCharacterViewHolder;

/**
 * Created by josedelpozo on 29/4/16.
 */
public class GoTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<GoTCharacter> characters;
    private CharacterPresenter presenter;

    public GoTAdapter(CharacterPresenter presenter) {
        this.characters = new ArrayList<>();
        this.presenter = presenter;
    }

    public void addAll(Collection<GoTCharacter> collection) {
        characters.clear();
        characters.addAll(collection);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.got_character_row, parent, false);
        return new GotCharacterViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GotCharacterViewHolder gotCharacterViewHolder = (GotCharacterViewHolder) holder;
        final GoTCharacter character = characters.get(position);
        gotCharacterViewHolder.render(character);
        ((GotCharacterViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(((GotCharacterViewHolder) holder).itemView.getContext(), DetailActivity.class);
                intent.putExtra("description", character.getDescription());
                intent.putExtra("name", character.getName());
                intent.putExtra("imageUrl", character.getImageUrl());
                ((GotCharacterViewHolder) holder).itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }
}
