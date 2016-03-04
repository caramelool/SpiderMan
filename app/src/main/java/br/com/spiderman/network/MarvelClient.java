package br.com.spiderman.network;

import java.util.concurrent.TimeUnit;

import br.com.spiderman.network.result.MarvelClientResult;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Caramelo on 02/03/2016.
 */
public class MarvelClient {
    private static Retrofit mRetrofit;

    public MarvelClient() {
        if (mRetrofit == null) {
            //Config okhttp
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.connectTimeout(1, TimeUnit.MINUTES);
            clientBuilder.readTimeout(1, TimeUnit.MINUTES);

            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://gateway.marvel.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientBuilder.build())
                    .build();
        }
    }

    public Interface getClient() {
        return mRetrofit.create(Interface.class);
    }

    public interface Interface {
        @GET("v1/public/characters/{character}/comics")
        Call<MarvelClientResult> returnComics(
                @Path("character") String character,
                @Query("ts") String ts,
                @Query("apikey") String apikey,
                @Query("hash") String hash);
    }
}
