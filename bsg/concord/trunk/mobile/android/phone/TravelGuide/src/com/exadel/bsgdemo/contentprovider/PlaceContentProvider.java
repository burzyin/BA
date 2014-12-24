package com.exadel.bsgdemo.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import com.exadel.bsgdemo.database.PlaceDatabaseHelper;
import com.exadel.bsgdemo.database.PlaceTable;

import java.util.Arrays;
import java.util.HashSet;

public class PlaceContentProvider extends ContentProvider {
    // database
    private PlaceDatabaseHelper database;

    // used for the UriMacher
    private static final int PLACES = 100;
    private static final int PLACE_ID = 200;

    private static final String AUTHORITY = "com.exadel.bsgdemo";

    private static final String BASE_PATH = "places";
    public static final Uri PLACES_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

//    public static final String PLACES_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/places";
//    public static final String PLACES_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/places";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, PLACES);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", PLACE_ID);
    }

    @Override
    public boolean onCreate() {
        database = new PlaceDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(PlaceTable.TABLE_PLACE);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case PLACES:
                break;
            case PLACE_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(PlaceTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        assert db != null;
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        assert cursor != null;
        Context context = getContext();
        assert context != null;
        cursor.setNotificationUri(context.getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id;
        switch (uriType) {
            case PLACES:
                assert sqlDB != null;
                id = sqlDB.insert(PlaceTable.TABLE_PLACE, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        Context context = getContext();
        assert context != null;
        context.getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted;
        assert sqlDB != null;
        switch (uriType) {
            case PLACES:
                rowsDeleted = sqlDB.delete(PlaceTable.TABLE_PLACE, selection, selectionArgs);
                break;
            case PLACE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(PlaceTable.TABLE_PLACE, PlaceTable.COLUMN_ID + "=" + id, null);
                } else {
                    rowsDeleted = sqlDB.delete(PlaceTable.TABLE_PLACE,
                            PlaceTable.COLUMN_ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        Context context = getContext();
        assert context != null;
        context.getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated;
        assert sqlDB != null;
        switch (uriType) {
            case PLACES:
                rowsUpdated = sqlDB.update(PlaceTable.TABLE_PLACE,
                        values,
                        selection,
                        selectionArgs);
                break;
            case PLACE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(PlaceTable.TABLE_PLACE,
                            values,
                            PlaceTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(PlaceTable.TABLE_PLACE,
                            values,
                            PlaceTable.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        Context context = getContext();
        assert context != null;
        context.getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = { PlaceTable.COLUMN_TITLE,
                PlaceTable.COLUMN_ICON, PlaceTable.COLUMN_LONGITUDE,
                PlaceTable.COLUMN_LATITUDE, PlaceTable.COLUMN_ID };
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }

}