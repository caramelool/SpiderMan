package br.com.spiderman.presenter;

import br.com.spiderman.domain.model.Profile;

/**
 * Created by Caramelo on 04/03/2016.
 */
public class ProfileActivityPresenterImpl
    implements ProfileActivityPresenter {

    ProfileActivityPresenter.View view;
    private Profile mProfile;

    public ProfileActivityPresenterImpl(View view) {
        this.view = view;
        getProfile();
        view.bind(mProfile);
    }

    @Override
    public Profile getProfile() {
        if (mProfile == null) {
            mProfile = new Profile("Lucas Caramelo Adevincolo de Olivera");
            mProfile.setUrlImage("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xtl1/v/t1.0-1/p160x160/12743617_981450971929446_8588284176885548315_n.jpg?oh=70f11aab76095cbd4d71da00965ab6df&oe=575F2629&__gda__=1465827272_de13b8c3c431fe5d68a18a1aad7a4d04");
            mProfile.setDescription(getDescription());
        }
        return mProfile;
    }

    private String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("23 anos, cristão, desenvolvedor Android a 3 anos, ");
        sb.append("ama o que faz(de verdade), curti jogos MMORPG e FPS, ");
        sb.append("contra-baixista a mais de 10 anos, ");
        sb.append("atualmente morando com os pais... ");
        sb.append("<br/><br/>");
        sb.append("Acho que é um resumo do resumo hehehe...");
        sb.append("<br/><br/>");
        sb.append("Espero poder ajudar bastante todo o time da Ginga.One ");
        sb.append("e poder crescer bastante profissionalmente também.");
        sb.append("<br/><br/>");
        sb.append("<b>Obrigado!!!</b>");
        return sb.toString();
    }
}
