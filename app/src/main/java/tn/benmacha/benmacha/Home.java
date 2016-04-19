package tn.benmacha.benmacha;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tn.benmacha.benmacha.Product.Categorie;
import tn.benmacha.benmacha.Product.Post;
import tn.benmacha.benmacha.Static.Resource;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout LinearLayout;
    private LayoutInflater LayoutInflater;
    private TextView TextCategories,SiteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        setContentView(R.layout.activity_home);

        TextCategories = (TextView) findViewById(R.id.TextCategorie);
        TextCategories.setText(Resource.SITE_NAME);

        LayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout = (LinearLayout) findViewById(R.id.LinearLayout);

        for (final Post Poste : Resource.Posts) {
            View custom = LayoutInflater.inflate(R.layout.post_preview, null);
            ImageView Image = (ImageView) custom.findViewById(R.id.postPicture);

            Picasso.with(getApplicationContext()).load(Poste.getFeaturedMedia()).into(Image);
            TextView title = (TextView) custom.findViewById(R.id.postTitle);
            title.setText(Poste.getTitle());
            final Intent PostView = new Intent(Home.this, PostView.class);

            PostView.putExtra("title",Poste.getTitle());
            PostView.putExtra("image", Poste.getFeaturedMedia());
            PostView.putExtra("text", Poste.getContent());


            custom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(PostView);
                }
            });
            LinearLayout.addView(custom);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        Menu m = navigationView.getMenu();

        SubMenu topChannelMenu = m.addSubMenu("القائمة");

        for (Categorie cat : Resource.Categories) {
            int espace = 20 - (cat.getPostCount().length() + cat.getNames().length() + 4);

            String Message = "";

            Message += cat.getPostCount();
            for (int i = 0; i < espace; i++)
                Message += " ";


            Message += cat.getNames();
            topChannelMenu.add(0, Integer.parseInt(cat.getID()), 0, Message).setIcon(R.drawable.categorie);

        }

        SubMenu buttomChannelMenu = m.addSubMenu("إعدادت خاصة");/*
        buttomChannelMenu.add(0, 960, 0, "إعدادات").setIcon(R.drawable.setting);
        buttomChannelMenu.add(0, 970, 0, "تحميل مواضيع جديدة").setIcon(R.drawable.refresh);
        buttomChannelMenu.add(0, 980, 0, "إتصل بنا").setIcon(R.drawable.mail); */
        buttomChannelMenu.add(0, 990, 0, "خروج").setIcon(R.drawable.logout);

        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case 990:
                exitAlert();
                break;
            case 980:
                break;
            case 970:
                break;
            case 960:
                break;
            default:
                getPostCategories(id);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void exitAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(Home.this).create();
        alertDialog.setTitle("خروج");
        alertDialog.setMessage("هل أنت متأكد ؟");
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.logout);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "نعم",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "لا",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void getPostCategories(int id) {
        LinearLayout.removeAllViews();

        for (Categorie cat : Resource.Categories)
            if (cat.getID().equals(String.valueOf(id))){
                TextCategories.setText(cat.getNames());

            }

        for (final Post Poste : Resource.Posts) {
            ArrayList<String> Categ = Poste.getCategorie();
            for (String categ : Categ){
                if (categ.equals(String.valueOf(id))){
                    View custom = LayoutInflater.inflate(R.layout.post_preview, null);
                    ImageView Image = (ImageView) custom.findViewById(R.id.postPicture);

                    Picasso.with(getApplicationContext()).load(Poste.getFeaturedMedia()).into(Image);
                    TextView title = (TextView) custom.findViewById(R.id.postTitle);
                    title.setText(Poste.getTitle());
                    final Intent PostView = new Intent(Home.this, PostView.class);

                    PostView.putExtra("title",Poste.getTitle());
                    PostView.putExtra("image", Poste.getFeaturedMedia());
                    PostView.putExtra("text", Poste.getContent());

                    custom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            startActivity(PostView);
                        }
                    });

                    LinearLayout.addView(custom);
                }
            }
        }
    }

}
