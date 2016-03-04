package br.com.spiderman.view.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.spiderman.R;
import br.com.spiderman.domain.model.Comic;
import br.com.spiderman.domain.model.Thumbnail;
import br.com.spiderman.presenter.ComicDetailPresenter;
import br.com.spiderman.presenter.ComicDetailPresenterImpl;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComicDetailActivity extends AppCompatActivity
    implements ComicDetailPresenter.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.app_bar)
    AppBarLayout appBarLayout;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.cardView)
    CardView cardView;
    @Bind(R.id.ivComic)
    ImageView ivComic;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvDescription)
    TextView tvDescription;
    @Bind(R.id.tvSaleDate)
    TextView tvSaleDate;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.tvPageCount)
    TextView tvPageCount;

    ComicDetailPresenter comicDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        ButterKnife.bind(this);

        comicDetailPresenter = new ComicDetailPresenterImpl(this);

        configActionBar(comicDetailPresenter.getComic());
    }

    public void configActionBar(Comic comic) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(comic.getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
    }

    @Override
    public void bind(Comic comic) {
        Glide.with(this)
                .load(comic.getThumbnail().getPath(Thumbnail.PORTRAIT_XLARGE))
                .into(ivComic);
        tvTitle.setText(comic.getTitle());
        tvDescription.setText(Html.fromHtml(comic.getDescription().trim()));
        tvSaleDate.setText(comic.getDates().get(0).getFormattedDate());
        tvPrice.setText(comic.getPrices().get(0).getFormattedPrice());
        tvPageCount.setText(String.valueOf(comic.getPageCount()));
    }

    @OnClick(R.id.cardView)
    public void onCardViewClick(View v) {
        Comic comic = comicDetailPresenter.getComic();

        Intent intent = new Intent(this, ComicImageActivity.class);
        intent.putExtra(Thumbnail.class.getSimpleName(), comic.getThumbnail().getPath(Thumbnail.PORTRAIT_UNCANNY));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // create the transition animation - the images in the layouts
            // of both activities are defined with android:transitionName="robot"
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, ivComic,
                            getString(R.string.transition_name_comic));
            // start the new activity
            startActivity(intent, options.toBundle());
        } else
            startActivity(intent);
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
