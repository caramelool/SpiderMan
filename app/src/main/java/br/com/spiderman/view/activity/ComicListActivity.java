package br.com.spiderman.view.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import br.com.spiderman.R;
import br.com.spiderman.domain.model.Comic;
import br.com.spiderman.presenter.ComicListFragmentPresenter;
import br.com.spiderman.presenter.ComicListFragmentPresenterImpl;
import br.com.spiderman.view.adapter.BaseAdapter;
import br.com.spiderman.view.adapter.ComicListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComicListActivity extends AppCompatActivity
    implements ComicListFragmentPresenter.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.llListEmpty)
    LinearLayout llListEmpty;

    ComicListFragmentPresenter comicListFragmentPresenter;
    private ComicListAdapter mComicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_list);

        ButterKnife.bind(this);

        configActionBar();

        comicListFragmentPresenter = new ComicListFragmentPresenterImpl(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,
                getResources().getInteger(R.integer.num_columns)));

        mComicListAdapter = new ComicListAdapter(this);
        recyclerView.setAdapter(mComicListAdapter);

        mComicListAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter<?> parent, View view, int position) {
                Intent intent = new Intent(ComicListActivity.this, ComicDetailActivity.class);

                // put extra selected comic
                intent.putExtra(Comic.class.getSimpleName(), mComicListAdapter.getItem(position));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // create the transition animation - the images in the layouts
                    // of both activities are defined with android:transitionName="robot"
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(ComicListActivity.this, view,
                                    getString(R.string.transition_name_comic));
                    // start the new activity
                    startActivity(intent, options.toBundle());
                } else
                    startActivity(intent);
            }
        });

        if (savedInstanceState == null)
            comicListFragmentPresenter.getComics();
    }

    private void configActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setIcon(R.drawable.img_marvel_logo);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public ArrayList<Comic> currentComics() {
        return mComicListAdapter.getItens();
    }

    @Override
    public void onLoadComics(ArrayList<Comic> comics) {
        llListEmpty.setVisibility(View.GONE);
        mComicListAdapter.addItens(comics);
    }

    @Override
    public void onError(Throwable t) {
        llListEmpty.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.llListEmpty)
    public void onListEmptyClick(View v) {
        comicListFragmentPresenter.getComics();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putParcelableArrayList("Lista", mComicListAdapter.getItens());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            ArrayList<Comic> comics = savedInstanceState.getParcelableArrayList("Lista");
            onLoadComics(comics);
        }
    }
}
