package br.com.spiderman.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Caramelo on 02/03/2016.
 */
public class ComicDate implements Parcelable {
    private String date;
    private String type;

    public ComicDate() {}

    public ComicDate(Parcel source) {
        date = source.readString();
        type = source.readString();
    }

    public static final Parcelable.Creator<ComicDate> CREATOR = new Parcelable.Creator<ComicDate>() {
        @Override
        public ComicDate createFromParcel(Parcel source) {
            return new ComicDate(source);
        }

        @Override
        public ComicDate[] newArray(int size) {
            return new ComicDate[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(type);
    }

    public String getDate() {
        return date;
    }

    public String getFormattedDate() {
        try {
            Locale locale = Locale.getDefault();
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", locale).parse("2012-05-20T09:00:00.000Z");
            return new SimpleDateFormat("MMMM dd, yyyy", locale).format(date);
        } catch (Exception e) {
            return date;
        }
    }

    public String getType() {
        return type;
    }
}
