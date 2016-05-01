package es.npatarino.android.gotchallenge.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ui.presenter.Presenter;

/**
 * Created by josedelpozo on 1/5/16.
 */
public abstract class BaseFragment extends Fragment implements Presenter.View{

    @Nullable
    @BindView(R.id.progress_bar)
    View loadingView;
    @Nullable
    @BindView(R.id.empty_case)
    TextView emptyCase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View itemView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, itemView);
        return itemView;
    }

    public abstract int getLayoutId();

    @Override
    public void showLoading(){
        if(loadingView != null) loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(){
        if(loadingView != null) loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyCase(){
        emptyCase.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(){

    }
}
