package es.npatarino.android.gotchallenge.ui.presenter;

/**
 * Created by josedelpozo on 1/5/16.
 */
public class Presenter<T extends Presenter.View> {

    private T view;

    public void setView(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }

    public void initialize() {

    }

    public void update() {

    }

    public interface View {
        void showLoading();

        void hideLoading();

        void showEmptyCase();

        void showError();
    }
}
