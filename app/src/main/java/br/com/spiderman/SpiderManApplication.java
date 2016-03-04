package br.com.spiderman;

import android.app.Application;

import java.util.Locale;

/**
 * Created by Caramelo on 03/03/2016.
 */
public class SpiderManApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Locale.setDefault(Locale.ENGLISH);
    }
}
