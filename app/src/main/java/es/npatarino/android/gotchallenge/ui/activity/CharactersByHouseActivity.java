package es.npatarino.android.gotchallenge.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.datasource.api.CharactersDataSource;
import es.npatarino.android.gotchallenge.datasource.cache.CacheStrategy;
import es.npatarino.android.gotchallenge.datasource.db.CharactersDaoImpl;
import es.npatarino.android.gotchallenge.datasource.db.dao.CharacterDao;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.repository.CharacterRepository;
import es.npatarino.android.gotchallenge.ui.adapter.CharactersAdapter;
import es.npatarino.android.gotchallenge.ui.loader.ImageLoader;
import es.npatarino.android.gotchallenge.ui.loader.PicassoImageLoader;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterByHousePresenter;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterListPresenter;
import es.npatarino.android.gotchallenge.usecase.GetCharactersByHouseId;

public class CharactersByHouseActivity extends BaseActivity implements CharacterListPresenter.View{

    @BindView(R.id.recycler_view)
    RecyclerView charactersRecyclerView;
    @BindView(R.id.house_image)
    ImageView houseImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private CharactersAdapter characterAdapter;

    CharacterByHousePresenter characterByHousePresenter;

    ImageLoader imageLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initializePresenter();
        initializeImageLoader();
        initializeAdapter();
        initializeRecyclerView();
        initializeUI();
        initializeToolbar();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_characters_by_house;
    }

    private void initializeToolbar(){
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initializeImageLoader(){
        imageLoader = new PicassoImageLoader();
    }

    private void initializeUI(){
        imageLoader.loadImage(getHouseImageUrl(), houseImage);

        setTitle(getHouseName());
    }

    private void initializePresenter(){
        CharactersDataSource charactersDataSource = new CharactersDataSource();
        CharacterDao characterDao = new CharactersDaoImpl(this);
        CacheStrategy cacheStrategy = new CacheStrategy(this);
        CharacterRepository characterRepository = CharacterRepository.getInstance(charactersDataSource,
                characterDao, cacheStrategy);
        GetCharactersByHouseId getCharactersByHouseId = new GetCharactersByHouseId(characterRepository);

        characterByHousePresenter = new CharacterByHousePresenter(getCharactersByHouseId);

        characterByHousePresenter.setView(this);
        characterByHousePresenter.setHouseId(getHouseId());
        characterByHousePresenter.initialize();
    }

    private void initializeAdapter(){
        characterAdapter = new CharactersAdapter(characterByHousePresenter, imageLoader);
    }

    private void initializeRecyclerView(){
        charactersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        charactersRecyclerView.setHasFixedSize(true);
        charactersRecyclerView.setAdapter(characterAdapter);
    }

    public String getHouseId(){
        return getIntent().getStringExtra(getString(R.string.house_id_extra));
    }

    public String getHouseName(){
        return getIntent().getStringExtra(getString(R.string.house_name_extra));
    }

    public String getHouseImageUrl(){
        return getIntent().getStringExtra(getString(R.string.house_image_url_extra));
    }

    @Override
    public void showCharacters(List<GoTCharacter> characters) {
        characterAdapter.addAll(characters);
    }

    @Override
    public void clickOnCharacter(GoTCharacter character, ImageView imageView) {
        Intent intent = new Intent(this, CharacterDetailActivity.class);
        intent.putExtra(getString(R.string.character_description_extra), character.getDescription());
        intent.putExtra(getString(R.string.character_name_extra), character.getName());
        intent.putExtra(getString(R.string.character_image_url_extra), character.getImageUrl());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, imageView, getString(R.string.character_image_trans));
            startActivity(intent, options.toBundle());
        }
        else {
            startActivity(intent);
        }
    }

    @Override
    public void clearAllCharacters() {
        characterAdapter.clearAll();
    }
}
