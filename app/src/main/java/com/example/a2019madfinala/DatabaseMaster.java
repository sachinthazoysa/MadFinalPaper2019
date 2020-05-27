package com.example.a2019madfinala;

import android.provider.BaseColumns;

public class DatabaseMaster {

    public DatabaseMaster() {
    }

        public static class Users implements BaseColumns {

        public  static final String TABLE_USERS = "users";
        public  static final String COLUMN_NAME_USERNAME = "userName";
        public  static final String COLUMN_NAME_PASSWORD = "password";
        public  static final String COLUMN_NAME_USER_TYPE = "userType";
    }

    public static class Movie implements BaseColumns{

        public  static final String TABLE_MOVIES = "movies";
        public  static final String COLUMN_NAME_MOVIE_NAME = "movieName";
        public  static final String COLUMN_NAME_MOVIE_YEAR = "movieYear";
    }

    public static class Comments implements BaseColumns{
        public  static final String TABLE_COMMENTS = "comments";
        public  static final String COLUMN_NAME_MOVIE_NAME = "movieName";
        public  static final String COLUMN_NAME_MOVIE_RATING = "movieRating";
        public  static final String COLUMN_NAME_COMMENTS = "comments";
    }

}
