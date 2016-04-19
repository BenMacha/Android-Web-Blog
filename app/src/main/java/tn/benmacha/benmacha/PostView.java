package tn.benmacha.benmacha;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class PostView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        Intent intent = getIntent();


        TextView ArticleTitre = (TextView) this.findViewById(R.id.ArticleTitre);
        ArticleTitre.setText(intent.getStringExtra("title"));

        WebView IntroBrowser = (WebView) findViewById(R.id.ArticleBrowser);
        IntroBrowser.loadData("<style>img{display: inline; height: auto; max-width: 100%;}</style>"+intent.getStringExtra("text"), "text/html; charset=utf-8","UTF-8");
       // TextView AritcleText = (TextView) this.findViewById(R.id.AritcleText);
        //AritcleText.setText(Html.fromHtml(intent.getStringExtra("text")));


    }


}
