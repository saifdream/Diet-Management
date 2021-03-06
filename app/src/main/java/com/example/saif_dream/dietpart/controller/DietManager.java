package com.example.saif_dream.dietpart.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.saif_dream.dietpart.DBHelper.DatabaseHelper;
import com.example.saif_dream.dietpart.model.Diet;

import java.util.ArrayList;

/**
 * Created by saif-dream on 4/20/2016.
 */
public class DietManager {
    private DatabaseHelper databaseHelper;
    private Diet diet;
    private SQLiteDatabase database;

    public DietManager(Context context) {
        databaseHelper= new DatabaseHelper(context);
    }

    private void open() {
        database = databaseHelper.getWritableDatabase();
    }

    private void close() {
        databaseHelper.close();
    }

    public boolean addDietInfo(Diet diet) {
        this.open();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_DIET_TYPE, diet.getDietType());
        contentValues.put(DatabaseHelper.COL_DIET_MENU, diet.getDietMenu());
        contentValues.put(DatabaseHelper.COL_DIET_DATE, diet.getDietDate());
        contentValues.put(DatabaseHelper.COL_TIME, diet.getDietTime());

        contentValues.put(DatabaseHelper.COL_DAILY_ALARM, diet.getDailyAlarm());
        contentValues.put(DatabaseHelper.COL_REMINDER, diet.getReminder());

        long inserted = database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        database.close();
        this.close();

        if (inserted > 0) {
            return true;

        } else return false;
    }

    public Diet getDietInfo(int id) {

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.COL_ID,
                DatabaseHelper.COL_DIET_TYPE, DatabaseHelper.COL_DIET_MENU, DatabaseHelper.COL_DIET_DATE,
                DatabaseHelper.COL_TIME,DatabaseHelper.COL_DAILY_ALARM, DatabaseHelper.COL_REMINDER},
                DatabaseHelper.COL_ID + " = " + id, null, null, null, null);

        cursor.moveToFirst();

        int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String dietType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_TYPE));
        String menuItem = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_MENU));
        String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_DATE));
        String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));

        String dailyAlarm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DAILY_ALARM));
        String reminder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_REMINDER));

        diet = new Diet(mId, dietType,menuItem,date,time,dailyAlarm,reminder);
        this.close();
        return diet;
    }

    public ArrayList<Diet> getDietByDate(int id, String givenDate) {

        this.open();
        //String selection = DatabaseHelper.COL_ID + " = " + id +" AND " + DatabaseHelper.COL_DIET_DATE + " = " + givenDate;
        String selection = DatabaseHelper.COL_DIET_DATE + " = '" + givenDate+"'";
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + selection + " ORDER BY "+ DatabaseHelper.COL_DIET_DATE + " ASC";

        ArrayList<Diet> dietList = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String dietType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_TYPE));
                String menuItem = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_MENU));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));
                String dailyAlarm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DAILY_ALARM));
                String reminder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_REMINDER));

                diet = new Diet(mId, dietType,menuItem,date,time,dailyAlarm,reminder);
                dietList.add(diet);
                cursor.moveToNext();
            }
        }
        this.close();
        return dietList;
    }

    public ArrayList<Diet> getDateRange( String flag){
        ArrayList<Diet> preDietList = new ArrayList<>();
        this.open();

        String operator;
        String today = (String) android.text.format.DateFormat.format("MM-dd-yyyy", new java.util.Date());
        String selection= "";

        if(flag.equals("Getter")){
            selection = DatabaseHelper.COL_DIET_DATE + " > '" + today +"'";
        } else selection = DatabaseHelper.COL_DIET_DATE + " < '" + today +"'";;

        //String selection = DatabaseHelper.COL_DIET_DATE + operator + " '" + format+"' ";
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + selection + " ORDER BY "+ DatabaseHelper.COL_DIET_DATE + " ASC";

        System.out.println(query);

        ArrayList<Diet> dietList = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String dietType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_TYPE));
                String menuItem = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_MENU));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));
                String dailyAlarm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DAILY_ALARM));
                String reminder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_REMINDER));

                diet = new Diet(mId, dietType,menuItem,date,time,dailyAlarm,reminder);
                preDietList.add(diet);
                cursor.moveToNext();
            }
        }
        this.close();

        return preDietList;
    }

    public ArrayList<Diet> getAllDietInfo() {
        this.open();
        ArrayList<Diet> dietList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, DatabaseHelper.COL_DIET_DATE + " ASC");

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String dietType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_TYPE));
                String menuItem = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_MENU));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DIET_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));
                String dailyAlarm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DAILY_ALARM));
                String reminder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_REMINDER));

                diet = new Diet(mId, dietType,menuItem,date,time,dailyAlarm,reminder);
                dietList.add(diet);
                cursor.moveToNext();
            }
        }
        this.close();
        return dietList;
    }

    public boolean updateDietInfo(int id, Diet diet) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_DIET_TYPE, diet.getDietType());
        contentValues.put(DatabaseHelper.COL_DIET_MENU, diet.getDietMenu());
        contentValues.put(DatabaseHelper.COL_DIET_DATE, diet.getDietDate());
        contentValues.put(DatabaseHelper.COL_TIME, diet.getDietTime());
        contentValues.put(DatabaseHelper.COL_DAILY_ALARM, diet.getDailyAlarm());
        contentValues.put(DatabaseHelper.COL_REMINDER, diet.getReminder());

        int updated = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.COL_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;

    }
}
