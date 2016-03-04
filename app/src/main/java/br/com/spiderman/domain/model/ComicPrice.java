package br.com.spiderman.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by Caramelo on 02/03/2016.
 */
public class ComicPrice implements Parcelable {
    private String type;
    private Double price;

    public ComicPrice() {}

    public ComicPrice(Parcel source) {
        type = source.readString();
        price = source.readDouble();
    }

    public static final Parcelable.Creator<ComicPrice> CREATOR = new Parcelable.Creator<ComicPrice>() {
        @Override
        public ComicPrice createFromParcel(Parcel source) {
            return new ComicPrice(source);
        }

        @Override
        public ComicPrice[] newArray(int size) {
            return new ComicPrice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeDouble(price);
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public String getFormattedPrice() {
        return DecimalFormat.getCurrencyInstance(Locale.getDefault()).format(price);
    }
}
