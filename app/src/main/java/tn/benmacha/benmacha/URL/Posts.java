package tn.benmacha.benmacha.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tn.benmacha.benmacha.Interface.GlobalInterface;
import tn.benmacha.benmacha.Product.Post;
import tn.benmacha.benmacha.Static.Resource;

public class Posts extends AsyncTask<String, String, String> {

    private Context Context;
    private GlobalInterface Interface;

    public Posts(Context Context, GlobalInterface Interface){
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
                Post Post = new Post();
                Post.setID(obj.getString("id"));

                ArrayList<String> idCatogrie = new ArrayList<>();

                JSONArray Categ = obj.getJSONArray("categories");
                for (int j = 0; j < Categ.length(); j++)
                    idCatogrie.add(Categ.getString(j));

                Post.setCategorie(idCatogrie);
                Post.setTitle(obj.getJSONObject("title").getString("rendered"));
                Post.setContent(obj.getJSONObject("content").getString("rendered"));
                Post.setDate(obj.getString("date"));
                Post.setFeaturedMedia(obj.getJSONObject("better_featured_image").getJSONObject("media_details").getJSONObject("sizes").getJSONObject("medium").getString("source_url"));

                Resource.Posts.add(Post);
            }
            Resource.Post = true ;
            Interface.onSuccess();
        }catch (JSONException e){
            Interface.onFail();
        }
    }
}
