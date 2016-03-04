package br.com.spiderman.presenter;

import br.com.spiderman.network.MarvelClient;
import br.com.spiderman.network.result.MarvelClientResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Caramelo on 02/03/2016.
 */
public class ComicListFragmentPresenterImpl
    implements ComicListFragmentPresenter {

    ComicListFragmentPresenter.View view;
    MarvelClient mMarvelClient;

    public ComicListFragmentPresenterImpl(View view) {
        this.view = view;
        mMarvelClient = new MarvelClient();
    }

    @Override
    public void getComics() {
        Call<MarvelClientResult> resultCall = mMarvelClient.getClient()
                .returnComics("1009610", "1", "bb4470a46d0659a43c566ac6056ed48d",
                        "479474cf0a28eac9998960da4d96f06b");
        resultCall.enqueue(new Callback<MarvelClientResult>() {
            @Override
            public void onResponse(Call<MarvelClientResult> call, Response<MarvelClientResult> response) {
                MarvelClientResult result = response.body();

                if (!result.getStatus().equalsIgnoreCase("OK"))
                    view.onError(new Exception("Not OK"));
                else if (view.currentComics().isEmpty() && result.getData().getComics().isEmpty())
                    view.onError(new Exception("Array empty"));

                view.onLoadComics(result.getData().getComics());
            }

            @Override
            public void onFailure(Call<MarvelClientResult> call, Throwable t) {
                t.printStackTrace();
                view.onError(t);
            }
        });
    }
}
