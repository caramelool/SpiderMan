package br.com.spiderman.domain.model;

import java.io.Serializable;

/**
 * Created by Caramelo on 02/03/2016.
 */
public class Thumbnail implements Serializable {

    public static final String PORTRAIT_SMALL = "portrait_small";
    public static final String PORTRAIT_MEDIUM = "portrait_medium";
    public static final String PORTRAIT_XLARGE = "portrait_xlarge";
    public static final String PORTRAIT_FANTASTIC = "portrait_fantastic";
    public static final String PORTRAIT_UNCANNY = "portrait_uncanny";
    public static final String PORTRAIT_INCREDIBLE = "portrait_incredible";

    private String path;
    private String extension;

    public String getPath(String portrait) {
        return String.format("%1$s/%2$s.%3$s", path, portrait, extension);
    }

    public String getExtension() {
        return extension;
    }
}
