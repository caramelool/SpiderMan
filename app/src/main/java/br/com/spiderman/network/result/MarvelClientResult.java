package br.com.spiderman.network.result;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import br.com.spiderman.domain.model.Comic;

/**
 * Created by Caramelo on 02/03/2016.
 */
public class MarvelClientResult {

    private String status;
    private Data data;

    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public class Data {
        @SerializedName("results")
        private ArrayList<Comic> comics;

        public ArrayList<Comic> getComics() {
            return comics;
        }
    }
}
