package com.exadel.bsgdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class MediaContentProvider extends ContentProvider {
	public static final String PROVIDER_NAME = "com.exadel.bsgdemo";
	public static final Uri PLACES_URI = Uri.parse("content://" + PROVIDER_NAME
			+ "/places");
	public static final String _ID = "_id";
	public static final String TITLE = "title";
	public static final String ICON = "icon";
	public static final String LONG = "long";
	public static final String LAT = "lat";
	

	private static final int ITEMS = 100;
	private static final int ITEMS_ID = 200;
	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(PROVIDER_NAME, "places", ITEMS);
		uriMatcher.addURI(PROVIDER_NAME, "places/#", ITEMS_ID);
	}
	// ---for database use---
	private SQLiteDatabase placesDB;
	private static final String DATABASE_NAME = "BSGDemo";
	private static final String PLACES_TABLE = "places";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table "
			+ PLACES_TABLE + 
			" (_id primary key not null, " +
			" title text not null," +
			" icon text default ''," +
			" long text not null," +
			" lat text not null)";

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("Content provider database",
					"Upgrading database from version " + oldVersion + "to "
							+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS titles");
			onCreate(db);
		}
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		int count = 0;
		switch (uriMatcher.match(arg0)) {
		case ITEMS:
			count = placesDB.delete(PLACES_TABLE, arg1, arg2);
			break;
		case ITEMS_ID:
			String id = arg0.getPathSegments().get(1);
			count = placesDB.delete(PLACES_TABLE, _ID + " = " + id
					+ (!TextUtils.isEmpty(arg1) ? " AND (" + arg1 + ')' : ""),
					arg2);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + arg0);
		}
		getContext().getContentResolver().notifyChange(arg0, null);
		return count;
	}

	// Returns the MIME type of the data at the given URI
	@Override
	public String getType(Uri arg0) {
		Log.d("hello", "with in the getType method");
		switch (uriMatcher.match(arg0)) {

		// ---get all books---
		case ITEMS:

			return "vnd.android.cursor.dir/vnd.contentprovider.books ";
			// ---get a particular book---
		case ITEMS_ID:
			Log.d("hello", "with in the getType method_1");
			return "vnd.android.cursor.item/vnd.contentprovider.books ";
		default:
			throw new IllegalArgumentException("Unsupported URI: " + arg0);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long rowID = placesDB.insert(PLACES_TABLE, "", values);
		// ---if added successfully---
		if (rowID > 0) {
			Uri _uri = ContentUris.withAppendedId(PLACES_URI, rowID);
			// ---notify register to change the content URI ---
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		throw new SQLException("Failed to insert row into " + uri);

	}

	@Override
	public boolean onCreate() {
		Context context = getContext();
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		placesDB = dbHelper.getWritableDatabase();
		return (placesDB == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
		
		sqlBuilder.setTables(PLACES_TABLE);

		if (uriMatcher.match(uri) == ITEMS_ID)
			sqlBuilder.appendWhere(_ID + "= " + uri.getPathSegments().get(1));
		
		Cursor c = sqlBuilder.query(placesDB, projection, selection,
				selectionArgs, null, null, null);
		
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;
		switch (uriMatcher.match(uri)) {
		case ITEMS:
			count = placesDB.update(PLACES_TABLE, values, selection,
					selectionArgs);
			break;
		case ITEMS_ID:
			count = placesDB.update(
					PLACES_TABLE,
					values,
					_ID
							+ " = "
							+ uri.getPathSegments().get(1)
							+ (!TextUtils.isEmpty(selection) ? " AND ("
									+ selection + ')' : ""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}