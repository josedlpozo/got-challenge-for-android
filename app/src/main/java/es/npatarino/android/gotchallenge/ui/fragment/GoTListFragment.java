package es.npatarino.android.gotchallenge.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.datasource.CharactersDataSource;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.repository.CharacterRepository;
import es.npatarino.android.gotchallenge.ui.adapter.GoTAdapter;
import es.npatarino.android.gotchallenge.ui.presenter.CharacterPresenter;
import es.npatarino.android.gotchallenge.usecase.GetAllCharacters;

/**
 * Created by josedelpozo on 29/4/16.
 */
public class GoTListFragment extends BaseFragment implements CharacterPresenter.View{

    private static final String TAG = "GoTListFragment";

    @BindView(R.id.recycler_view)
    RecyclerView characterRecyclerView;

    GoTAdapter characterAdapter;

    CharacterPresenter characterPresenter;

    public GoTListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        initializeAdapter();
        initializeRecyclerView();
        initializePresenter();

        /*new Thread(new Runnable() {

            @Override
            public void run() {
                String url = "http://52.18.228.107:3000/characters";

                URL obj = null;
                try {
                    obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    Type listType = new TypeToken<ArrayList<GoTCharacter>>() {
                    }.getType();
                    final List<GoTCharacter> characters = new Gson().fromJson(response.toString(), listType);
                    GoTListFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            characterAdapter.addAll(characters);
                            characterAdapter.notifyDataSetChanged();
                            hideLoading();
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }


            }
        }).start();*/
        return rootView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    private void initializeAdapter(){
        characterAdapter = new GoTAdapter();
    }

    private void initializeRecyclerView(){
        characterRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        characterRecyclerView.setHasFixedSize(true);
        characterRecyclerView.setAdapter(characterAdapter);
    }

    private void initializePresenter(){
        CharactersDataSource charactersDataSource = new CharactersDataSource();
        CharacterRepository characterRepository = new CharacterRepository(charactersDataSource);
        GetAllCharacters getAllCharacters = new GetAllCharacters(characterRepository);

        characterPresenter = new CharacterPresenter(getAllCharacters);

        characterPresenter.setView(this);
        characterPresenter.initialize();
    }

    @Override
    public void showCharacters(List<GoTCharacter> characters) {
        characterAdapter.addAll(characters);
    }
}
