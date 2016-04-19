package tn.benmacha.benmacha;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import tn.benmacha.benmacha.Interface.GlobalInterface;
import tn.benmacha.benmacha.URL.Global;
import tn.benmacha.benmacha.Static.Resource;
import tn.benmacha.benmacha.URL.Categories;
import tn.benmacha.benmacha.URL.Posts;


public class Intro extends AppCompatActivity {

    private WebView IntroBrowser;
    private Context Context ;
    private GlobalInterface GlobalInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
        Context = getApplicationContext();

        IntroBrowser = (WebView) findViewById(R.id.IntroBrowser);
        //IntroBrowser.addJavascriptInterface(new MyJavaScriptInterface(this), "AndroidFunction");
        IntroBrowser.getSettings().setJavaScriptEnabled(true);
        IntroBrowser.loadUrl("file:///android_asset/www/intro.html");
        IntroBrowser.setWebViewClient(new WebViewClient() {
            private int running = 0; // Could be public if you want a timer to check.

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                running++;
                IntroBrowser.loadUrl(urlNewString);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                running = Math.max(running, 1); // First request move it to 1.
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (--running == 0) {
                   Loading();
                    //  IntroBrowser.loadUrl("javascript:setVariable('title','dsdsdsdsdds')");
                }
            }
        });

    }


    public void Loading(){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            GlobalInterface = new GlobalInterface(){

                @Override
                public void onSuccess() {
                    if (Resource.Categorie && Resource.Post)
                        openHome();
                }

                @Override
                public void onFail() {
                    alertDialog();
                }
            };

            new Global(Context, new GlobalInterface(){

                @Override
                public void onSuccess() {
                    IntroBrowser.loadUrl("javascript:setVariable('title','"+Resource.SITE_NAME+"')");
                    new Posts(Context, GlobalInterface).execute(Resource.POST_URL);
                    new Categories(Context, GlobalInterface).execute(Resource.CATEGORIE_URL);
                }

                @Override
                public void onFail() {
                    alertDialog();
                }
            }).execute(Resource.HOME_URL);


        } else {
            alertDialog();
        }
    }


    private void openHome(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   Thread.sleep(3000);
                   Intent homepage = new Intent(Context, Home.class);
                   startActivity(homepage);
                   IntroBrowser = null;
                   finish();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();
    }

    private void alertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(Intro.this).create();
        alertDialog.setTitle("خلل خارجي");
        alertDialog.setMessage("أنت لست متصل بالأنترنت");
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.no_network);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "خروج",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

}