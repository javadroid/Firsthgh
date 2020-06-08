package com.example.akologaji.first;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.akologaji.first.data.CourseContract.courseEntry;


import com.example.akologaji.first.data.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.akologaji.first.data.CourseContract.courseEntry.COLUMN_COURSE_CODE;
import static com.example.akologaji.first.data.CourseContract.courseEntry.COLUMN_COURSE_NAME;
import static com.example.akologaji.first.data.CourseContract.courseEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mCodeEditText;
    private EditText mDepartEditText;
    private EditText mLevelEditText;
    private EditText mSemEditText;
    private EditText mSecEditText;
    private EditText mNPriceEditText;
    private Button mAdd;
    private Button mView;
    private Button mList;
    long newRowId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.IdName);
        mCodeEditText= (EditText) findViewById(R.id.idCode);
        mDepartEditText= (EditText) findViewById(R.id.idDepart);
        mLevelEditText= (EditText) findViewById(R.id.idLevel);
        mSemEditText= (EditText) findViewById(R.id.idSem);
        mSecEditText= (EditText) findViewById(R.id.idSec);
        mNPriceEditText= (EditText) findViewById(R.id.idPrice);
        mAdd= (Button) findViewById(R.id.idAddDB);
        mView= (Button) findViewById(R.id.idViewDB);
        mList=(Button) findViewById(R.id.idViewList);
        Button mDelete = (Button) findViewById(R.id.iddDB);


        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeString = mCodeEditText.getText().toString().trim();

if(codeString.isEmpty()){

}
else {


}

            }
        });

   mView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, ViewDbActivity.class);
        startActivity(intent);
    }
});
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDB();
            }
        });
        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }



    private void insertDB() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String codeString = mCodeEditText.getText().toString().trim();
        String departString = mDepartEditText.getText().toString().trim();
        String levelString = mLevelEditText.getText().toString().trim();
        String semString = mSemEditText.getText().toString().trim();
        String secString = mSecEditText.getText().toString().trim();
        String priceString = mNPriceEditText.getText().toString().trim();

        // Create database helper
        DBHelper mDbHelper = new DBHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        // Create a ContentValues object where column names are the keys,

        ContentValues values = new ContentValues();
        values.put(courseEntry.COLUMN_COURSE_NAME, nameString);
        values.put(courseEntry.COLUMN_COURSE_CODE, codeString);
        values.put(courseEntry.COLUMN_COURSE_DEPARTMENT, departString);
        values.put(courseEntry.COLUMN_COURSE_LEVEL, levelString);
        values.put(courseEntry.COLUMN_COURSE_SEMESTER, semString);
        values.put(courseEntry.COLUMN_COURSE_SECTION, secString);
        values.put(courseEntry.COLUMN_COURSE_PRICE, priceString);

        // Insert a new row for  the database, returning the ID of that new row.
        newRowId = db.insert(courseEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving ", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteDB(){
        // Create database helper
        DBHelper mDbHelper = new DBHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        db.delete(TABLE_NAME,COLUMN_COURSE_CODE,null);

    }


}









