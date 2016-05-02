package es.npatarino.android.gotchallenge.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.datasource.CharactersDataSource;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.repository.CharacterRepository;
import es.npatarino.android.gotchallenge.ui.adapter.CharactersAdapter;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterByHousePresenter;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterListPresenter;
import es.npatarino.android.gotchallenge.usecase.GetCharactersByHouseId;

public class CharactersByHouseActivity extends BaseActivity implements CharacterListPresenter.View{

    @BindView(R.id.recycler_view)
    RecyclerView charactersRecyclerView;

    private CharactersAdapter characterAdapter;

    CharacterByHousePresenter characterByHousePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_characters_by_house;
    }

    private void initializePresenter(){
        CharactersDataSource charactersDataSource = new CharactersDataSource();
        CharacterRepository characterRepository = CharacterRepository.getInstance(charactersDataSource);
        GetCharactersByHouseId getCharactersByHouseId = new GetCharactersByHouseId(characterRepository);

        characterByHousePresenter = new CharacterByHousePresenter(getCharactersByHouseId);

        characterByHousePresenter.setView(this);
        characterByHousePresenter.setHouseId(getHouseId());
        characterByHousePresenter.initialize();
    }

    private void initializeAdapter(){
        characterAdapter = new CharactersAdapter(characterByHousePresenter);
    }

    private void initializeRecyclerView(){
        charactersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        charactersRecyclerView.setHasFixedSize(true);
        charactersRecyclerView.setAdapter(characterAdapter);
    }

    public String getHouseId(){
        return getIntent().getStringExtra("house_id");
    }

    @Override
    public void showCharacters(List<GoTCharacter> characters) {
        characterAdapter.addAll(characters);
    }

    @Override
    public void clickOnCharacter(GoTCharacter character) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("description", character.getDescription());
        intent.putExtra("name", character.getName());
        intent.putExtra("imageUrl", character.getImageUrl());
        startActivity(intent);
    }

    @Override
    public void clearAllCharacters() {
        characterAdapter.clearAll();
    }
}
