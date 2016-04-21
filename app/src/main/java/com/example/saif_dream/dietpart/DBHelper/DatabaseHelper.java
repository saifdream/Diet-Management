package com.example.saif_dream.dietpart.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by saif-dream on 4/20/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "family_care";
    public static final String TABLE_NAME = "diet_management_table";
    public static final String COL_ID = "id";
    public static final String COL_DIET_TYPE = "diet_type";
    public static final String COL_DIET_MENU = "diet_menu";
    public static final String COL_DIET_DATE = "diet_date";
    public static final String COL_TIME = "diet_time";
    public static final String COL_DAILY_ALARM = "daily_alarm";
    public static final String COL_REMINDER = "reminder";

    public String CREATE_DIET_TABLE = " CREATE TABLE " + TABLE_NAME +
            " ( " + COL_ID +" INTEGER PRIMARY KEY,"
                + COL_DIET_TYPE +" TEXT,"
                + COL_DIET_MENU +" TEXT,"
                + COL_DIET_DATE +" TEXT,"
                + COL_TIME+" TEXT,"
                + COL_DAILY_ALARM +" TEXT,"
                + COL_REMINDER +" TEXT " +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
