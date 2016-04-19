package tn.benmacha.benmacha.Static;

import java.util.ArrayList;

import tn.benmacha.benmacha.Product.Categorie;
import tn.benmacha.benmacha.Product.Post;

public class Resource {
/*
    public static final String HOME_URL         = "http://10.0.2.2/json/first";
    public static final String CATEGORIE_URL    = "http://10.0.2.2/json/categories";
    public static final String POST_URL         = "http://10.0.2.2/json/posts";
*/
    public static final String HOME_URL         = "https://www.benmacha.tn/wp-json/";
    public static final String CATEGORIE_URL    = "https://www.benmacha.tn/wp-json/wp/v2/categories?order=asc&orderby=id";
    public static final String POST_URL         = "https://www.benmacha.tn/wp-json/wp/v2/posts?per_page=100&order=desc&orderby=id";

    public static String SITE_NAME = "";

    public static ArrayList<Categorie> Categories = new ArrayList<>();
    public static Boolean Categorie = false;

    public static ArrayList<Post> Posts = new ArrayList<>();
    public static Boolean Post = false;

}
