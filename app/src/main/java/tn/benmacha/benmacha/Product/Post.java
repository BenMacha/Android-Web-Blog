package tn.benmacha.benmacha.Product;

import java.util.ArrayList;

public class Post {

    private String Content;
    private String featuredMedia;
    private String date;
    private String title;
    private String ID;
    private ArrayList<String> Categorie ;

    public Post() {
    }

    public void setFeaturedMedia(String featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public String getFeaturedMedia() {
        return featuredMedia;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public ArrayList<String> getCategorie() {
        return Categorie;
    }

    public void setCategorie(ArrayList<String> categorie) {
        Categorie = categorie;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
