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
import tn.benmacha.benmacha.Static.Resource;


public class Global extends AsyncTask<String, String, String> {

    private Context Context;
    private GlobalInterface Interface;

    public Global(Context Context, GlobalInterface Interface){
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
            return "NULL";
        }


    }

    @Override
    protected void onPostExecute(String json) {
        try {
            JSONObject array = new JSONObject(json);

            if (array.length() > 0) {
                Log.d("Global", array.getString("name"));

                Resource.SITE_NAME = array.getString("name");
                Interface.onSuccess();
            }else
                Interface.onFail();

        } catch (JSONException e) {
            Interface.onFail();
        }

    }

}

