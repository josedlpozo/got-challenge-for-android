package es.npatarino.android.gotchallenge.ui.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.ui.loader.ImageLoader;
import es.npatarino.android.gotchallenge.ui.loader.PicassoImageLoader;

public class CharacterDetailActivity extends BaseActivity {


    private static final String TAG = "CharacterDetailActivity";

    @BindView(R.id.character_name)
    TextView characterName;
    @BindView(R.id.character_image)
    ImageView characterImage;
    @BindView(R.id.character_description)
    TextView characterDescription;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ImageLoader imageLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initializeUI();
        initializeToolbar();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }


    private String getCharacterDescription(){
        return getIntent().getStringExtra(getString(R.string.character_description_extra));
    }

    private String getCharacterName(){
        return getIntent().getStringExtra(getString(R.string.character_name_extra));
    }

    private String getCharacterImageUrl(){
        return getIntent().getStringExtra(getString(R.string.character_image_url_extra));
    }

    private void initializeUI(){
        imageLoader = new PicassoImageLoader();
        imageLoader.loadImage(getCharacterImageUrl(), characterImage);

        characterDescription.setText(getCharacterDescription());

        characterName.setText(getCharacterName());
    }

    private void initializeToolbar(){
        toolbar.setTitle(getCharacterName());
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
