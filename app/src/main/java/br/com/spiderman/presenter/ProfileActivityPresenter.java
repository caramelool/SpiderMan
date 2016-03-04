package br.com.spiderman.presenter;

import br.com.spiderman.domain.model.Profile;

/**
 * Created by Caramelo on 04/03/2016.
 */
public interface ProfileActivityPresenter extends Presenter {
    Profile getProfile();
    interface View {
        void bind(Profile profile);
    }
}
