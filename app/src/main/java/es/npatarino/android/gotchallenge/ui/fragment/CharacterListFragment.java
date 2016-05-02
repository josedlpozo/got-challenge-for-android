package es.npatarino.android.gotchallenge.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.datasource.CharactersDataSource;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.repository.CharacterRepository;
import es.npatarino.android.gotchallenge.ui.activity.CharacterDetailActivity;
import es.npatarino.android.gotchallenge.ui.adapter.CharactersAdapter;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterListPresenter;
import es.npatarino.android.gotchallenge.usecase.GetAllCharacters;
import es.npatarino.android.gotchallenge.usecase.GetCharactersByQuery;

/**
 * Created by josedelpozo on 29/4/16.
 */
public class CharacterListFragment extends BaseFragment implements CharacterListPresenter.View, SearchView.OnQueryTextListener{

    private static final String TAG = "CharacterListFragment";

    @BindView(R.id.recycler_view)
    RecyclerView characterRecyclerView;

    CharactersAdapter characterAdapter;

    CharacterListPresenter characterListPresenter;

    SearchView searchView;

    public CharacterListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        initializePresenter();
        initializeAdapter();
        initializeRecyclerView();

        return rootView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    private void initializeAdapter(){
        characterAdapter = new CharactersAdapter(characterListPresenter);
    }

    private void initializeRecyclerView(){
        characterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        characterRecyclerView.setHasFixedSize(true);
        characterRecyclerView.setAdapter(characterAdapter);
    }

    private void initializePresenter(){
        CharactersDataSource charactersDataSource = new CharactersDataSource();
        CharacterRepository characterRepository = CharacterRepository.getInstance(charactersDataSource);
        GetAllCharacters getAllCharacters = new GetAllCharacters(characterRepository);
        GetCharactersByQuery getCharactersByQuery = new GetCharactersByQuery(characterRepository);

        characterListPresenter = new CharacterListPresenter(getAllCharacters, getCharactersByQuery);

        characterListPresenter.setView(this);
        characterListPresenter.initialize();
    }

    @Override
    public void showCharacters(List<GoTCharacter> characters) {
        characterAdapter.addAll(characters);
    }

    @Override
    public void clickOnCharacter(GoTCharacter character) {
        Intent intent = new Intent(getContext(), CharacterDetailActivity.class);
        intent.putExtra("description", character.getDescription());
        intent.putExtra("name", character.getName());
        intent.putExtra("image_url", character.getImageUrl());
        getContext().startActivity(intent);
    }

    @Override
    public void clearAllCharacters() {
        characterAdapter.clearAll();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        characterListPresenter.searchCharactersByQuery(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        characterListPresenter.searchCharactersByQuery(newText);
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        /*SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconified(false);
        searchView.setQueryHint(getString(R.string.search_hint));*/
    }

}
