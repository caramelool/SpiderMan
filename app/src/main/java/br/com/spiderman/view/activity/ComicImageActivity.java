package br.com.spiderman.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import br.com.spiderman.R;
import br.com.spiderman.domain.model.Thumbnail;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ComicImageActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.cardView)
    CardView cardView;
    @Bind(R.id.ivComic)
    ImageView ivComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_image);

        ButterKnife.bind(this);

        configActionBar();

        String image = getIntent().getStringExtra(Thumbnail.class.getSimpleName());
        Glide.with(this)
                .load(image)
                .into(ivComic);
    }

    public void configActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            supportFinishAfterTransition();
        }

        return super.onOptionsItemSelected(item);
    }

}
