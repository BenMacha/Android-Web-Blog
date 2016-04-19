package tn.benmacha.benmacha.Product;

public class Categorie {

    private String ID, PostCount;
    private String Name, URL;

    public Categorie() {}

    public String getNames() {
        return Name;
    }

    public void setNames(String name) {
        Name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPostCount() {
        return PostCount;
    }

    public void setPostCount(String postCount) {
        PostCount = postCount;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
