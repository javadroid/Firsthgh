package com.example.akologaji.first;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akologaji.first.data.CourseContract;
import com.example.akologaji.first.data.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.akologaji.first.data.CourseContract.courseEntry.COLUMN_COURSE_NAME;

public class ListActivity extends AppCompatActivity {
    DBHelper mDbHelper = new DBHelper(this);
    Spinner Sdepart ;
    Spinner Slevel ;
    Spinner Ssec ;
    Spinner Ssem ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Sdepart=(Spinner)findViewById(R.id.Sdapart);
        Slevel=(Spinner)findViewById(R.id.SLevel);
        Ssec=(Spinner)findViewById(R.id.SSec);
        Ssem=(Spinner)findViewById(R.id.SSem);

        Sdepart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    String SelectedDeart=(String)  adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        displayDatabaseColumn();
    }




    private void displayDatabaseColumn() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CourseContract.courseEntry.COLUMN_COURSE_DEPARTMENT,
                CourseContract.courseEntry.COLUMN_COURSE_LEVEL,
                CourseContract.courseEntry.COLUMN_COURSE_SEMESTER,
                CourseContract.courseEntry.COLUMN_COURSE_SECTION,
        };

        // Perform a query on the table
        Cursor cursor = db.query(
                CourseContract.courseEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order



        try {


            // Figure out the index of each column

            int departColumnIndex = cursor.getColumnIndex(CourseContract.courseEntry.COLUMN_COURSE_DEPARTMENT);
            int levelColumnIndex = cursor.getColumnIndex(CourseContract.courseEntry.COLUMN_COURSE_LEVEL);
            int semColumnIndex = cursor.getColumnIndex(CourseContract.courseEntry.COLUMN_COURSE_SEMESTER);
            int secColumnIndex = cursor.getColumnIndex(CourseContract.courseEntry.COLUMN_COURSE_SECTION);

            ArrayList<String> stringsDepart =new ArrayList<>();
            ArrayList<String> stringsLevel =new ArrayList<>();
            ArrayList<String> stringsSem =new ArrayList<>();
            ArrayList<String> stringsSec =new ArrayList<>();


            stringsDepart.add( "Select Department");
            stringsLevel.add( "Select Level");
            stringsSec.add( "Select Section");
            stringsSem.add( "Select Semester");
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.

                String currentName = cursor.getString(departColumnIndex);
                String currentLevel = cursor.getString(levelColumnIndex);
                String currentSec = cursor.getString(secColumnIndex);
                String currentSem = cursor.getString(semColumnIndex);


                // Display the values from each column of the current row in the cursor in the TextView

                stringsDepart.add( currentName);
                stringsLevel.add( currentLevel);
                stringsSec.add( currentSec);
                stringsSem.add( currentSem);
                ArrayAdapter<String> adapterDepart =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,stringsDepart);
                ArrayAdapter<String> adapterLevel =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,stringsLevel);
                ArrayAdapter<String> adapterSec =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,stringsSec);
                ArrayAdapter<String> adapterSem =new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,stringsSem);


                Sdepart.setAdapter(adapterDepart);
                Slevel.setAdapter(adapterLevel);
                Ssec.setAdapter(adapterSec);
                Ssem.setAdapter(adapterSem);



            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

}
