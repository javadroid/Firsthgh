package com.example.akologaji.first.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.example.akologaji.first.data.CourseContract.courseEntry;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.akologaji.first.data.CourseContract.courseEntry.COLUMN_COURSE_NAME;
import static com.example.akologaji.first.data.CourseContract.courseEntry.TABLE_NAME;

/**
 * Created by Akolo Gaji on 6/2/2020.
 */

public class DBHelper extends SQLiteOpenHelper {

        public static final String LOG_TAG = DBHelper.class.getSimpleName();


        private static final String DATABASE_NAME = "shelter.db";


        private static final int DATABASE_VERSION = 1;


        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * This is called when the database is created for the first time.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create a String that contains the SQL statement to create the pets table
            String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + courseEntry.TABLE_NAME + " ("
                    + courseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_COURSE_NAME+ " TEXT, "
                    + courseEntry.COLUMN_COURSE_CODE + " TEXT , "
                    + courseEntry.COLUMN_COURSE_DEPARTMENT + " TEXT, "
                    + courseEntry.COLUMN_COURSE_LEVEL + " TEXT, "
                    + courseEntry.COLUMN_COURSE_SEMESTER + " TEXT, "
                    + courseEntry.COLUMN_COURSE_SECTION + " TEXT, "
                    + courseEntry.COLUMN_COURSE_PRICE + " TEXT);";

            // Execute the SQL statement
            db.execSQL(SQL_CREATE_PETS_TABLE);
        }

        /**
         * This is called when the database needs to be upgraded.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // The database is still at version 1, so there's nothing to do be done here.
        }


    }