package com.example.a2019madfinala;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MovieApp.db";
    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String SQL_CREATE_USERS_ENTRIES =
                "CREATE TABLE " + DatabaseMaster.Users.TABLE_USERS + " (" +
                        DatabaseMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        DatabaseMaster.Users.COLUMN_NAME_USERNAME+ " TEXT," +
                        DatabaseMaster.Users.COLUMN_NAME_USER_TYPE+ " TEXT," +
                        DatabaseMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";



        String SQL_CREATE_MOVIES_ENTRIES =
                "CREATE TABLE " + DatabaseMaster.Movie.TABLE_MOVIES + " (" +
                        DatabaseMaster.Movie._ID + " INTEGER PRIMARY KEY," +
                        DatabaseMaster.Movie.COLUMN_NAME_MOVIE_NAME+ " TEXT," +
                        DatabaseMaster.Movie.COLUMN_NAME_MOVIE_YEAR + " TEXT)";


        String SQL_CREATE_COMMENTS_ENTRIES =
                "CREATE TABLE " + DatabaseMaster.Comments.TABLE_COMMENTS + " (" +
                        DatabaseMaster.Comments._ID + " INTEGER PRIMARY KEY," +
                        DatabaseMaster.Comments.COLUMN_NAME_COMMENTS+ " TEXT," +
                        DatabaseMaster.Comments.COLUMN_NAME_MOVIE_RATING+ " INTEGER," +
                        DatabaseMaster.Comments.COLUMN_NAME_MOVIE_NAME + " TEXT)";

        db.execSQL(SQL_CREATE_USERS_ENTRIES);
        db.execSQL(SQL_CREATE_MOVIES_ENTRIES);
        db.execSQL(SQL_CREATE_COMMENTS_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public Long registerUser(String username, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.Users.COLUMN_NAME_USERNAME, username);
        values.put(DatabaseMaster.Users.COLUMN_NAME_PASSWORD, password);

        long newRowId = db.insert(DatabaseMaster.Users.TABLE_USERS, null, values);

        return newRowId;
    }

    public int loginUser(String username,String password){
        Boolean value = false;

        if(username.equals("admin")){
            return 1;
        }
        else {
            SQLiteDatabase db = getReadableDatabase();

            String[] projection = {
                    DatabaseMaster.Users._ID,
                    DatabaseMaster.Users.COLUMN_NAME_USERNAME,
                    DatabaseMaster.Users.COLUMN_NAME_PASSWORD
            };


            String selection = DatabaseMaster.Users.COLUMN_NAME_USERNAME + " = ?";

            String[] selectionArgs = { username};


            Cursor cursor = db.query(
                    DatabaseMaster.Users.TABLE_USERS,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            );


            while(cursor.moveToNext() && !value) { //fjhkafhkdhakdsfjkahsdlkfhalfhlkfhaksj
                String userName =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_USERNAME));
                String Password =cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Users.COLUMN_NAME_PASSWORD));

                value = username.equals(userName) && password.equals(Password);
            }
            cursor.close();
            if(value){
                return 2;
            }
            else
                return 0;

        }


    }


    public void readAllInfo() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                DatabaseMaster.Movie.COLUMN_NAME_MOVIE_NAME,
                DatabaseMaster.Movie.COLUMN_NAME_MOVIE_YEAR
//                DatabaseMaster.Comments.COLUMN_NAME_COMMENTS
        };

        Cursor cursor = db.query(
                DatabaseMaster.Users.TABLE_USERS, // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List movieNames = new ArrayList<>();
        List movieYears = new ArrayList<>();


        while (cursor.moveToNext()) { //fjhkafhkdhakdsfjkahsdlkfhalfhlkfhaksj
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Movie.COLUMN_NAME_MOVIE_NAME));
            String year = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Movie.COLUMN_NAME_MOVIE_YEAR));

            movieNames.add(name);
            movieYears.add(year);
        }
        cursor.close();
    }


    public boolean addMovies(String name, String year){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.Movie.COLUMN_NAME_MOVIE_NAME, name);
        values.put(DatabaseMaster.Movie.COLUMN_NAME_MOVIE_YEAR, year);

        long movID = db.insert(DatabaseMaster.Movie.TABLE_MOVIES, null, values);

        if(movID==-1){
            return false;
        }else{
            return true;
        }
    }


    public void viewMovies(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                DatabaseMaster.Movie.COLUMN_NAME_MOVIE_NAME,
        };

        Cursor cursor = db.query(
                DatabaseMaster.Movie.TABLE_MOVIES, // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List movies = new ArrayList<>();

        while (cursor.moveToNext()) { //fjhkafhkdhakdsfjkahsdlkfhalfhlkfhaksj
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Movie.COLUMN_NAME_MOVIE_NAME));
            movies.add(name);
        }
        cursor.close();
    }

    public boolean addComments(String name,String comment, int rating){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DatabaseMaster.Comments.COLUMN_NAME_MOVIE_NAME, name);
        values.put(DatabaseMaster.Comments.COLUMN_NAME_COMMENTS,comment);
        values.put(DatabaseMaster.Comments.COLUMN_NAME_MOVIE_RATING,rating);

        long comID = db.insert(DatabaseMaster.Comments.TABLE_COMMENTS, null, values);

        if(comID==-1){
            return false;
        }else{
            return true;
        }
    }

    public void viewComments(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                DatabaseMaster.Comments.COLUMN_NAME_COMMENTS
        };

        Cursor cursor = db.query(
                DatabaseMaster.Comments.TABLE_COMMENTS, // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List comments = new ArrayList<>();


        while (cursor.moveToNext()) {
            String com = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Movie.COLUMN_NAME_MOVIE_NAME));
            comments.add(com);
        }
        cursor.close();
    }


}
