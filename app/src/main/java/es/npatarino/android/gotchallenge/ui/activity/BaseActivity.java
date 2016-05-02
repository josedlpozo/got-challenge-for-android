package es.npatarino.android.gotchallenge.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ui.presenter.Presenter;

/**
 * Created by josedelpozo on 2/5/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements Presenter.View {

    @Nullable
    @BindView(R.id.progress_bar)
    View loadingView;
    @Nullable
    @BindView(R.id.empty_case)
    TextView emptyCase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    public abstract int getLayoutId();

    @Override
    public void showLoading() {
        if (loadingView != null) loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (loadingView != null) loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyCase() {
        if (emptyCase != null) emptyCase.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_case), Toast.LENGTH_SHORT).show();
    }
}
