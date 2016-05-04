package es.npatarino.android.gotchallenge.ui.loader;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by josedelpozo on 4/5/16.
 */
public class PicassoImageLoader implements ImageLoader {

    @Override
    public void loadImage(String imageUrl, ImageView imageView) {
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }
}
