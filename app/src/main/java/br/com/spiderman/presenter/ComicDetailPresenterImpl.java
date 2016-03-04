package br.com.spiderman.presenter;

import android.content.Intent;

import br.com.spiderman.domain.model.Comic;

/**
 * Created by Caramelo on 03/03/2016.
 */
public class ComicDetailPresenterImpl
    implements ComicDetailPresenter {

    ComicDetailPresenter.View view;
    private Comic mComic;

    public ComicDetailPresenterImpl(ComicDetailPresenter.View view) {
        this.view = view;
        getComic();
        view.bind(mComic);
    }

    @Override
    public Comic getComic() {
        if (mComic == null) {
            Intent intent = view.getIntent();
            mComic = intent.getParcelableExtra(Comic.class.getSimpleName());
        }
        return mComic;
    }
}
