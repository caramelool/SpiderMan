package br.com.spiderman.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Caramelo on 02/03/2016.
 */
public class Comic implements Parcelable {
    private int id;
    private float issueNumber;
    private String title;
    private String description;
    private ArrayList<ComicPrice> prices;
    private ArrayList<ComicDate> dates;
    private int pageCount;
    private Thumbnail thumbnail;

    public Comic() {}

    public Comic(Parcel source) {
        prices = new ArrayList<>();
        dates = new ArrayList<>();
        id = source.readInt();
        issueNumber = source.readFloat();
        title = source.readString();
        description = source.readString();
        source.readTypedList(prices, ComicPrice.CREATOR);
        source.readTypedList(dates, ComicDate.CREATOR);
        pageCount = source.readInt();
        thumbnail = (Thumbnail) source.readSerializable();
    }

    public static final Parcelable.Creator<Comic> CREATOR = new Parcelable.Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel source) {
            return new Comic(source);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeFloat(issueNumber);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeTypedList(prices);
        dest.writeTypedList(dates);
        dest.writeInt(pageCount);
        dest.writeSerializable(thumbnail);
    }

    public int getId() {
        return id;
    }

    public float getIssueNumber() {
        return issueNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public ArrayList<ComicPrice> getPrices() {
        return prices;
    }

    public ArrayList<ComicDate> getDates() {
        return dates;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }
}
