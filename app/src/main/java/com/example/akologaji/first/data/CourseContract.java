package com.example.akologaji.first.data;

import android.provider.BaseColumns;

/**
 * Created by Akolo Gaji on 6/2/2020.
 */

public class CourseContract {
    
        private CourseContract() {}

        /**
         * Inner class that defines constant values for the  database table.
         * Each entry in the table represents a single course.
         */
        public static final class courseEntry implements BaseColumns {

            /** Name of database table for course */
            public final static String TABLE_NAME = "courses";

            /**
             * Unique ID number for the course (only for use in the database table).
             *
             * Type: INTEGER
             */
            public final static String _ID = BaseColumns._ID;

            /**
             * Name of the course.
             *
             * Type: TEXT
             */
            public final static String COLUMN_COURSE_NAME ="CCname";


            public final static String COLUMN_COURSE_CODE = "CCcode";


            public final static String COLUMN_COURSE_DEPARTMENT = "CCdepart";


            public final static String COLUMN_COURSE_LEVEL = "CClevel";


            public final static String COLUMN_COURSE_SEMESTER = "CCsem";


            public final static String COLUMN_COURSE_SECTION = "CCsec";


            public final static String COLUMN_COURSE_PRICE = "CCprice";


        }

    }


