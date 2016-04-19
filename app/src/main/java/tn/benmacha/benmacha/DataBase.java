package tn.benmacha.benmacha;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBase extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "DB";
    private SQLiteDatabase myDataBase;
    private final Context Context;

    public DataBase(Context Context) {

        super(Context, DB_NAME, null, 1);
        this.Context = Context;
        this.DB_PATH = "/data/data/" + Context.getApplicationContext().getPackageName() + "/databases/";
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //hhhhhhhhhh
        } else {

            this.getReadableDatabase();

            try {

                //Open your local db as the input stream
                InputStream myInput = Context.getAssets().open(DB_NAME);

                // Path to the just created empty db
                String outFileName = DB_PATH + DB_NAME;

                //Open the empty db as the output stream
                OutputStream myOutput = new FileOutputStream(outFileName);

                //transfer bytes from the inputfile to the outputfile
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                //Close the streams
                myOutput.flush();
                myOutput.close();
                myInput.close();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    public boolean checkDataBase() {

        int Categorie = 0;
        int Post = 0;
        try {
            String myPath = DB_PATH + DB_NAME;
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            Categorie = getCount("Categorie");
            Post = getCount("Post");
            myDataBase.close();
        } catch (SQLiteException e) {
            Log.e("SQLiteException",e.getMessage());

        }

        if(Categorie > 0 && Post > 0)
            return true;
        else
            return false;
    }


    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

    public int getCount(String request) {
        Cursor Cursor = myDataBase.rawQuery("Select count(*) As total from " + request, null);
        Cursor.moveToFirst();
        return Cursor.getInt(Cursor.getColumnIndex("total"));
    }

    public void Insert(String request) {
        myDataBase.execSQL(request);
    }

    public Cursor Selection(String sql) {
        return myDataBase.rawQuery(sql ,null);
    }



}