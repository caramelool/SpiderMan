package br.com.spiderman.presenter;

import java.util.ArrayList;

import br.com.spiderman.domain.model.Comic;

/**
 * Created by Caramelo on 02/03/2016.
 */
public interface ComicListFragmentPresenter extends Presenter {
    void getComics();
    interface View {
        ArrayList<Comic> currentComics();
        void onLoadComics(ArrayList<Comic> comics);
        void onError(Throwable t);
    }
}
