package br.com.spiderman.presenter;

import android.content.Intent;

import br.com.spiderman.domain.model.Comic;

/**
 * Created by Caramelo on 03/03/2016.
 */
public interface ComicDetailPresenter extends Presenter {
    Comic getComic();
    interface View {
        Intent getIntent();
        void bind(Comic comic);
    }
}
