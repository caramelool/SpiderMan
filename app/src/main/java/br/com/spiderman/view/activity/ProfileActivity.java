package br.com.spiderman.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.spiderman.R;
import br.com.spiderman.domain.model.Profile;
import br.com.spiderman.domain.util.CircleTransform;
import br.com.spiderman.presenter.ProfileActivityPresenter;
import br.com.spiderman.presenter.ProfileActivityPresenterImpl;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity
    implements ProfileActivityPresenter.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.ivImage)
    ImageView ivImage;

    @Bind(R.id.tvName)
    TextView tvName;

    @Bind(R.id.tvDescription)
    TextView tvDescription;

    ProfileActivityPresenter profileActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        profileActivityPresenter = new ProfileActivityPresenterImpl(this);

        configActionBar();
    }

    public void configActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.action_profile));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void bind(Profile profile) {
        Glide.with(this)
                .load(profile.getUrlImage())
                .transform(new CircleTransform(this))
                .into(ivImage);
        tvName.setText(profile.getName());
        tvDescription.setText(Html.fromHtml(profile.getDescription()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
