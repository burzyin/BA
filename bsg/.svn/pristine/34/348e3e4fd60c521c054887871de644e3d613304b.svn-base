package com.exadel.bsgdemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:22 AM, 12/12/13
 */
public class PlaceDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "places.db";
    private static final int DATABASE_VERSION = 1;

    public PlaceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        PlaceTable.onCreate(database);
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        PlaceTable.onUpgrade(database, oldVersion, newVersion);
    }

}
