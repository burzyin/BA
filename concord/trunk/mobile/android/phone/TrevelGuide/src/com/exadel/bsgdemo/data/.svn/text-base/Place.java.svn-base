package com.exadel.bsgdemo.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.exadel.bsgdemo.MediaContentProvider;

public class Place {
	private static final String ID = MediaContentProvider._ID;
	private static final String TITLE = MediaContentProvider.TITLE;
	private static final String ICON = MediaContentProvider.ICON;
	private static final String LONG = MediaContentProvider.LONG;
	private static final String LAT = MediaContentProvider.LAT;

	private long id;
	private String title;
	private String iconPath;
	private String longtitude;
	private String latitude;

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
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
			String title = placeNode.getAttributes().getNamedItem(ID)
					.getNodeValue();
			String id = placeNode.getAttributes().getNamedItem(TITLE)
					.getNodeValue();
			String icon = placeNode.getAttributes().getNamedItem(ICON)
					.getNodeValue();
			
			this.iconPath = icon;
			this.id = Long.valueOf(id);
			this.title = title;
		} else
			throw new IllegalArgumentException(
					"Cant find place tag in xml document");
	}
	public Place(JSONObject object) {
		if (object != null) {
			try{
				String title = object.getString(TITLE);
				long id = object.getLong(ID);
				String icon = object.getString(ICON);
				
				String longtitude = object.getString(LONG);
				String lat = object.getString(LAT);
	
				this.iconPath = icon;
				this.id = id;
				this.title = title;
				this.longtitude = longtitude;
				this.latitude = lat;
			}catch (JSONException e) {
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
				String title = placeNode.getAttributes().getNamedItem(TITLE)
						.getNodeValue();
				String id = placeNode.getAttributes().getNamedItem(ID)
						.getNodeValue();
				String icon = placeNode.getAttributes().getNamedItem(ICON)
						.getNodeValue();

				this.iconPath = icon;
				this.id = Long.valueOf(id);
				this.title = title;
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
			String title = place.getAttributes().getNamedItem(TITLE)
					.getNodeValue();
			String id = place.getAttributes().getNamedItem(ID)
					.getNodeValue();
			String icon = place.getAttributes().getNamedItem(ICON)
					.getNodeValue();

			this.iconPath = icon;
			this.id = Long.valueOf(id);
			this.title = title;
		} else
			throw new IllegalArgumentException(
					"Cant find place tag in xml document");
	}

	public Place(Cursor dataBaseRepresentation) {
		if(dataBaseRepresentation != null){
			this.id = dataBaseRepresentation.getLong(dataBaseRepresentation.getColumnIndex(ID));
			this.title = dataBaseRepresentation.getString(dataBaseRepresentation.getColumnIndex(TITLE));
			this.iconPath = dataBaseRepresentation.getString(dataBaseRepresentation.getColumnIndex(ICON));
			this.longtitude = dataBaseRepresentation.getString(dataBaseRepresentation.getColumnIndex(LONG));
			this.latitude = dataBaseRepresentation.getString(dataBaseRepresentation.getColumnIndex(LAT));
		}
		//dataBaseRepresentation.close();
	}

	public void moveToDatabese(Context c) {
		/*Uri selfUri = ContentUris.withAppendedId(MediaContentProvider.PLACES_URI, Long.valueOf(id));
		Cursor cursor = c.getContentResolver().query(selfUri, null, null, null, null); 
		try{
			if(cursor.moveToNext()){
				Log.e("Unable to add place", "Such place is allready in database. Call update instead");
				return;
			}
		}finally{
			if(cursor != null)
				cursor.close();
		}*/
		
		
		ContentValues cv = new ContentValues();
		cv.put(ID, this.id);
		cv.put(TITLE, this.title);
		cv.put(ICON, this.iconPath);
		cv.put(LONG, this.longtitude);
		cv.put(LAT, this.latitude);
		
		Uri res = c.getContentResolver().insert(MediaContentProvider.PLACES_URI, cv);
		Log.d("place moved to database", res.toString());
	}

	public void deleteFromDatabese(Context c) {

	}

	public void updateRecordInDataBase(Context c) {

	}

	private Document getDomElement(String xml) {
		Document doc = null;
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

	/*public static List<Place> serializeList(InputStream input) {
		List<Place> result = null;
		DocumentBuilder db;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = db.parse(input);
			NodeList placeNodes = document.getElementsByTagName("place");
			for (int i = 0; i < placeNodes.getLength(); i++) {
				if (placeNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					if (result == null)
						result = new ArrayList<Place>();
					result.add(new Place((Element)placeNodes.item(i)));
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}*/
	
	public static List<Place> serializeList(InputStream input) {
		List<Place> result = new ArrayList<Place>();
		try {
			StringBuilder inputStringBuilder = new StringBuilder();
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
			String line = bufferedReader.readLine();
	        while(line != null){
	            inputStringBuilder.append(line);inputStringBuilder.append('\n');
	            line = bufferedReader.readLine();
	        }
	        
	        JSONObject root = new JSONObject(inputStringBuilder.toString());
	        JSONArray places = root.getJSONArray("places-list");
	        for (int i = 0; i < places.length(); i++) {
	        	try{
	        		Place pl = new Place(places.getJSONObject(i));
	        		result.add(pl);
	        	}catch (Exception e) {
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
	@Override
	public boolean equals(Object o) {
		return this.id == ((Place)o).getId();
	}
}