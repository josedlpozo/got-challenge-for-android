package es.npatarino.android.gotchallenge.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;

public class DetailActivity extends BaseActivity {


    private static final String TAG = "DetailActivity";

    @BindView(R.id.character_name)
    TextView characterName;
    @BindView(R.id.character_image)
    ImageView characterImage;
    @BindView(R.id.character_description)
    TextView characterDescription;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        return getIntent().getStringExtra("description");
    }

    private String getCharacterName(){
        return getIntent().getStringExtra("name");
    }

    private String getCharacterImageUrl(){
        return getIntent().getStringExtra("image_url");
    }

    private void initializeUI(){
        Picasso.with(this).load(getCharacterImageUrl()).into(characterImage);

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
