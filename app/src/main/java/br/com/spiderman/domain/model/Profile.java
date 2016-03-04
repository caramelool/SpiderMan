package br.com.spiderman.domain.model;

/**
 * Created by Caramelo on 04/03/2016.
 */
public class Profile {
    private String urlImage;
    private String name;
    private String description;

    public Profile(String name) {
        this.name = name;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
