package es.npatarino.android.gotchallenge.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
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
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.ui.adapter.GoTHouseAdapter;

/**
 * Created by josedelpozo on 29/4/16.
 */
public class GoTHousesListFragment extends Fragment {

    private static final String TAG = "GoTHousesListFragment";

    @BindView(R.id.progressBar)
    ContentLoadingProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView housesRecyclerView;

    GoTHouseAdapter housesAdapter;

    public GoTHousesListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rootView);
        initializeAdapter();
        initializeRecyclerView();

        new Thread(new Runnable() {

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
                    GoTHousesListFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<GoTHouse> hs = new ArrayList<GoTHouse>();
                            for (int i = 0; i < characters.size(); i++) {
                                boolean b = false;
                                for (int j = 0; j < hs.size(); j++) {
                                    if (hs.get(j).getHouseName().equalsIgnoreCase(characters.get(i).getHouseName())) {
                                        b = true;
                                    }
                                }
                                if (!b) {
                                    if (characters.get(i).getHouseId() != null && !characters.get(i).getHouseId().isEmpty()) {
                                        GoTHouse h = new GoTHouse();
                                        h.setHouseId(characters.get(i).getHouseId());
                                        h.setHouseName(characters.get(i).getHouseName());
                                        h.setHouseImageUrl(characters.get(i).getHouseImageUrl());
                                        hs.add(h);
                                        b = false;
                                    }
                                }
                            }
                            housesAdapter.addAll(hs);
                            housesAdapter.notifyDataSetChanged();
                            progressBar.hide();
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            }
        }).start();
        return rootView;
    }

    private void initializeAdapter(){
        housesAdapter = new GoTHouseAdapter(getActivity());
    }

    private void initializeRecyclerView(){
        housesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        housesRecyclerView.setHasFixedSize(true);
        housesRecyclerView.setAdapter(housesAdapter);
    }
}
