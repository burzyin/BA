package com.exadel.bsgdemo.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.exadel.bsgdemo.contentprovider.PlaceContentProvider;
import com.exadel.bsgdemo.database.PlaceTable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class Place {
    private long id;
    private String title;
    private String iconPath;
    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Place(String xmlRepresentation) {
        Document document = getDomElement(xmlRepresentation);
        Node placeNode = document.getElementsByTagName("place").item(0);
        if (placeNode != null) {

            String id = placeNode.getAttributes().getNamedItem(PlaceTable.COLUMN_TITLE)
                    .getNodeValue();
            this.id = Long.valueOf(id);

            this.iconPath = placeNode.getAttributes().getNamedItem(PlaceTable.COLUMN_ICON)
                    .getNodeValue();
            this.title = placeNode.getAttributes().getNamedItem(PlaceTable.COLUMN_ID)
                    .getNodeValue();
        } else
            throw new IllegalArgumentException(
                    "Cant find place tag in xml document");
    }

    public Place(JSONObject object) {
        if (object != null) {
            try {
                String title = object.getString(PlaceTable.COLUMN_TITLE);
                long id = object.getLong(PlaceTable.COLUMN_ID);
                String icon = object.getString(PlaceTable.COLUMN_ICON);

                String longitude = object.getString(PlaceTable.COLUMN_LONGITUDE);
                String latitude = object.getString(PlaceTable.COLUMN_LATITUDE);

                this.iconPath = icon;
                this.id = id;
                this.title = title;
                this.longitude = longitude;
                this.latitude = latitude;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            throw new IllegalArgumentException(
                    "Cant find place tag in xml document");
    }

    public Place(InputStream streamRepresentation) {
        DocumentBuilder db;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = db.parse(streamRepresentation);
            Node placeNode = document.getElementsByTagName("place").item(0);
            if (placeNode != null) {

                String id = placeNode.getAttributes().getNamedItem(PlaceTable.COLUMN_ID)
                        .getNodeValue();
                this.id = Long.valueOf(id);

                this.iconPath = placeNode.getAttributes().getNamedItem(PlaceTable.COLUMN_ICON)
                        .getNodeValue();
                this.title = placeNode.getAttributes().getNamedItem(PlaceTable.COLUMN_TITLE)
                        .getNodeValue();
            } else
                throw new IllegalArgumentException(
                        "Cant find place tag in xml document");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Place(Element place) {
        if (place != null) {
            String id = place.getAttributes().getNamedItem(PlaceTable.COLUMN_ID)
                    .getNodeValue();
            this.id = Long.valueOf(id);

            this.iconPath = place.getAttributes().getNamedItem(PlaceTable.COLUMN_ICON)
                    .getNodeValue();
            this.title = place.getAttributes().getNamedItem(PlaceTable.COLUMN_TITLE)
                    .getNodeValue();
        } else
            throw new IllegalArgumentException(
                    "Cant find place tag in xml document");
    }

    public Place(Cursor dataBaseRepresentation) {
        if (dataBaseRepresentation != null) {
            this.id = dataBaseRepresentation.getLong(dataBaseRepresentation.getColumnIndex(PlaceTable.COLUMN_ID));
            this.title = dataBaseRepresentation.getString(dataBaseRepresentation.getColumnIndex(PlaceTable.COLUMN_TITLE));
            this.iconPath = dataBaseRepresentation.getString(dataBaseRepresentation.getColumnIndex(PlaceTable.COLUMN_ICON));
            this.longitude = dataBaseRepresentation.getString(dataBaseRepresentation.getColumnIndex(PlaceTable.COLUMN_LONGITUDE));
            this.latitude = dataBaseRepresentation.getString(dataBaseRepresentation.getColumnIndex(PlaceTable.COLUMN_LATITUDE));
        }
//        dataBaseRepresentation.close();
    }

    public void moveToDatabase(Context context) {
        Uri selfUri = ContentUris.withAppendedId(PlaceContentProvider.PLACES_URI, id);
        assert selfUri != null;
        Cursor cursor = context.getContentResolver().query(selfUri, null, null, null, null);
        try {
            assert cursor != null;
            if (cursor.moveToNext()) {
                Log.e("Unable to add place", "Such place is already in database. Call update instead");
                updateRecordInDatabase(context);
                return;
            }
        } finally {
            if (cursor != null){
                cursor.close();
            }
        }

        ContentValues contentValues = getContentValues();

        Uri uri = context.getContentResolver().insert(PlaceContentProvider.PLACES_URI, contentValues);
        assert uri != null;
        Log.d("place moved to database", uri.toString());
    }

    public void deleteFromDatabase(Context c) {
        int count = c.getContentResolver().delete(PlaceContentProvider.PLACES_URI, null, null);
        Log.d("deleted", count + " rows");
    }

    public boolean checkInDatabase(Context c) {
        String[] args = new String[] { String.valueOf(getId())};
        int count = c.getContentResolver().query(PlaceContentProvider.PLACES_URI, null, PlaceTable.COLUMN_ID + " = ?", args, "_id").getCount();
        Log.d("found", count + " rows");
        return count > 0 ? true : false;
    }

    public void updateRecordInDatabase(Context c) {
        ContentValues contentValues = getContentValues();

        String[] args = new String[] { String.valueOf(getId())};
        int count = c.getContentResolver().update(PlaceContentProvider.PLACES_URI, contentValues, PlaceTable.COLUMN_ID + " = ?", args);
        Log.d("updated", count + " rows");
    }

    private ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlaceTable.COLUMN_ID, this.id);
        contentValues.put(PlaceTable.COLUMN_TITLE, this.title);
        contentValues.put(PlaceTable.COLUMN_ICON, this.iconPath);
        contentValues.put(PlaceTable.COLUMN_LONGITUDE, this.longitude);
        contentValues.put(PlaceTable.COLUMN_LATITUDE, this.latitude);
        return contentValues;
    }

    private Document getDomElement(String xml) {
        Document doc;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }
        return doc;
    }

    public static List<Place> serializeList(InputStream input) {
        List<Place> result = new ArrayList<Place>();
        try {
            StringBuilder inputStringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }

            JSONObject root = new JSONObject(inputStringBuilder.toString());
            JSONArray places = root.getJSONArray("places-list");
            for (int i = 0; i < places.length(); i++) {
                try {
                    Place pl = new Place(places.getJSONObject(i));
                    result.add(pl);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}