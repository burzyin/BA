package com.exadel.bsgdemo.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:11 AM, 12/12/13
 */
public class PlaceTable {

    // Database table
    public static final String TABLE_PLACE = "place";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ICON = "icon";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_PLACE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_ICON + " text not null,"
            + COLUMN_LONGITUDE + " text not null,"
            + COLUMN_LATITUDE + " text not null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(PlaceTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACE);
        onCreate(database);
    }
}