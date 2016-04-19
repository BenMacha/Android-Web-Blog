package tn.benmacha.benmacha.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tn.benmacha.benmacha.Interface.GlobalInterface;
import tn.benmacha.benmacha.Product.Categorie;
import tn.benmacha.benmacha.Static.Resource;

public class Categories extends AsyncTask<String, String, String> {

    private Context Context;
    private GlobalInterface Interface;

    public Categories(Context Context, GlobalInterface Interface){
        this.Context = Context;
        this.Interface = Interface;
    }

    @Override
    protected String doInBackground(String... args) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(args[0])
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "NULL";
        }


    }

    @Override
    protected void onPostExecute(String json) {
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Categorie categ = new Categorie();
                categ.setID(obj.getString("id"));
                categ.setNames(obj.getString("name"));
                categ.setPostCount(obj.getString("count"));
                categ.setURL(obj.getString("link"));

                Resource.Categories.add(categ);
            }
            Resource.Categorie = true;
            Interface.onSuccess();
        } catch (JSONException e) {
            Interface.onFail();
        }

    }

}

