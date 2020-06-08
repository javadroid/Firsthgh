package com.example.akologaji.first;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.akologaji.first.data.DBHelper;
import com.example.akologaji.first.data.CourseContract.courseEntry;

public class ViewDbActivity extends AppCompatActivity {
    DBHelper mDbHelper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_db);



        displayDatabaseInfo();


    }










    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                courseEntry.COLUMN_COURSE_NAME,
        courseEntry.COLUMN_COURSE_CODE,
        courseEntry.COLUMN_COURSE_DEPARTMENT,
        courseEntry.COLUMN_COURSE_LEVEL,
        courseEntry.COLUMN_COURSE_SEMESTER,
        courseEntry.COLUMN_COURSE_SECTION,
        courseEntry.COLUMN_COURSE_PRICE, };

        // Perform a query on the table
        Cursor cursor = db.query(
                courseEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {
            // Create a header in the Text View that looks like this:
            //

           //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The course table contains " + cursor.getCount() + " Courses.\n\n");
            displayView.append(courseEntry._ID + " - " +
                            courseEntry.COLUMN_COURSE_NAME+ " - " +
                    courseEntry.COLUMN_COURSE_CODE+ " - " +
                    courseEntry.COLUMN_COURSE_DEPARTMENT+ " - " +
                    courseEntry.COLUMN_COURSE_LEVEL+ " - " +
                    courseEntry.COLUMN_COURSE_SEMESTER+ " - " +
                    courseEntry.COLUMN_COURSE_SECTION+ " - " +
                    courseEntry.COLUMN_COURSE_PRICE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(courseEntry._ID);

            int nameColumnIndex = cursor.getColumnIndex(courseEntry.COLUMN_COURSE_NAME);
            int codeColumnIndex = cursor.getColumnIndex(courseEntry.COLUMN_COURSE_CODE);
            int departColumnIndex = cursor.getColumnIndex(courseEntry.COLUMN_COURSE_DEPARTMENT);
            int levelColumnIndex = cursor.getColumnIndex(courseEntry.COLUMN_COURSE_LEVEL);
            int semColumnIndex = cursor.getColumnIndex(courseEntry.COLUMN_COURSE_SEMESTER);
            int secColumnIndex = cursor.getColumnIndex(courseEntry.COLUMN_COURSE_SECTION);
            int priceColumnIndex = cursor.getColumnIndex(courseEntry.COLUMN_COURSE_PRICE );

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex+1);
                String currentName = cursor.getString(nameColumnIndex);
                String currentcode = cursor.getString(codeColumnIndex);
                String currentdepart = cursor.getString(departColumnIndex);
                String currentLevel = cursor.getString(levelColumnIndex);
                String currentsem = cursor.getString(semColumnIndex);
                String currentsec = cursor.getString(secColumnIndex);
                String currentprice = cursor.getString(priceColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " \n- " +
                        currentName + " \n- " +
                        currentcode + " \n- " +
                        currentdepart + "\n - " +
                        currentLevel + " \n- " +
                        currentsem + " \n- " +
                        currentsec + " \n- " +
                        currentprice+"\n"));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }



}






