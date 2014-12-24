package com.exadel.bsgdemo.near;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.bonuspack.overlays.ExtendedOverlayItem;
import org.osmdroid.bonuspack.overlays.ItemizedOverlayWithBubble;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.bonuspack.routing.RoadNode;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.PathOverlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.place.PlaceDescriptionActivity;

public class OpenStreetMapFragment extends android.support.v4.app.Fragment{
	ItemizedIconOverlay myItemizedOverlay;
	org.osmdroid.views.overlay.MyLocationOverlay myLocationOverlay;
	List<Place> places;
	public OpenStreetMapFragment(List<Place> places){
		this.places = places;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.open_map_layout, null);
		final org.osmdroid.views.MapView map = (MapView) v.findViewById(R.id.open_map_view);
		map.setBuiltInZoomControls(true);
		
		//map.getController().setZoom(15);
		map.getController().setCenter(new GeoPoint(53.923426, 27.614397));
		map.setMultiTouchControls(true);
		map.setMapListener(new MapListener() {
			
			@Override
			public boolean onZoom(ZoomEvent arg0) {
				try{
					map.getOverlays().remove(myItemizedOverlay);
				}catch (UnsupportedOperationException e) {
					e.printStackTrace();
				}
				Drawable marker=getResources().getDrawable(android.R.drawable.star_big_on);
		        int markerWidth = marker.getIntrinsicWidth();
		        int markerHeight = marker.getIntrinsicHeight();
		        marker.setBounds(0, markerHeight, markerWidth, 0);
		        
		        ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getActivity());
		        
		        myItemizedOverlay = new org.osmdroid.views.overlay.ItemizedIconOverlay<OverlayItem>(new ArrayList<OverlayItem>(),
		                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>()
		                {
		                    @Override
		                    public boolean onItemSingleTapUp(final int index, final OverlayItem item){
		                    		startActivity(new Intent(getActivity(), PlaceDescriptionActivity.class));
		                		return true; 
		                    }

		                    @Override
		                    public boolean onItemLongPress(final int index, final OverlayItem item)
		                    {
		                    	Toast.makeText(getActivity(), item.getTitle() + " long click", Toast.LENGTH_SHORT).show();
		                        return true;
		                    }
		                }, resourceProxy);

		        map.getOverlays().add(myItemizedOverlay);
		        if(places != null){
		        	for(int i = 0; i < places.size(); i++)
					    try{
					    	Place p = places.get(i);
					        org.osmdroid.views.overlay.OverlayItem nextItem = new org.osmdroid.views.overlay.OverlayItem(p.getTitle(), String.valueOf(p.getId()), new org.osmdroid.util.GeoPoint(Float.valueOf(p.getLongtitude()), Float.valueOf(p.getLatitude())));
					        Drawable dr;
					        if(p.getIconPath() != null)
					        	dr = new BitmapDrawable(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BSGDemo/" + p.getIconPath());	
							else 
								dr = getResources().getDrawable(R.drawable.botanic_garden);
							
					        int zoomLevel = arg0.getZoomLevel();
					        
					        Bitmap bitmap = getBitmapWithPin(((BitmapDrawable) dr).getBitmap());
					        try{
					        	Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (zoomLevel * zoomLevel)/3, (zoomLevel * zoomLevel)/3, true));
					        	nextItem.setMarker(d);
					        }catch (Exception e) {
								e.getMessage();
							}
					        myItemizedOverlay.addItem(nextItem);
					    }catch (NullPointerException ex){
					        Log.e("exadel", "Error while adding place markers to map. " + ex.getMessage());
					    }
		        }
				return true;
			}
			
			@Override
			public boolean onScroll(ScrollEvent arg0) {
				return true;
			}
		});
		
		myLocationOverlay = new org.osmdroid.views.overlay.MyLocationOverlay(getActivity(), map);
        myLocationOverlay.enableMyLocation();
        map.getOverlays().add(myLocationOverlay);

        MapController myMapController = map.getController();
        myMapController.setZoom(12);
        
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(getActivity());
        map.getOverlays().add(myScaleBarOverlay);
//        if(myItemizedOverlay.size() > 0)
//            map.getController().setCenter(myItemizedOverlay.getItem(0).getPoint());
        
//        new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				RoadManager roadManager = new OSRMRoadManager();
//				
//				ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
//		        waypoints.add(new GeoPoint(53.923426, 27.614397));
//		        waypoints.add(new GeoPoint(47.991075,37.806499));
//		        final Road road = roadManager.getRoad(waypoints);
//				PathOverlay roadOverlay = RoadManager.buildRoadOverlay(road, map.getContext());
//		        map.getOverlays().add(roadOverlay);
		        
//				final ArrayList<ExtendedOverlayItem> roadItems = 
//						  new ArrayList<ExtendedOverlayItem>();
//						ItemizedOverlayWithBubble<ExtendedOverlayItem> roadNodes = 
//						  new ItemizedOverlayWithBubble<ExtendedOverlayItem>(getActivity(), roadItems, map);
//						map.getOverlays().add(roadNodes);
//						
//				        Drawable marker = getResources().getDrawable(R.drawable.pin2);
//				        for (int i=0; i<road.mNodes.size(); i++){
//				                RoadNode node = road.mNodes.get(i);
//				                ExtendedOverlayItem nodeMarker = new ExtendedOverlayItem("Step "+i, "", node.mLocation, getActivity());
//				                nodeMarker.setMarkerHotspot(OverlayItem.HotspotPlace.CENTER);
//				                nodeMarker.setMarker(marker);
//				                roadNodes.addItem(nodeMarker);
//				        }		
//		        h.post(new Runnable() {
//					
//					@Override
//					public void run() {
//						map.invalidate();
//						Toast.makeText(getActivity(), "length - " + road.mLength, Toast.LENGTH_SHORT).show();
//					}
//				});
//			}
//		}).start();
		
		return v;
	};
	
	private Handler h = new Handler();
	
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//	}
//	@Override
//	public void onDetach() {
//		super.onDetach();
//		myLocationOverlay.disableMyLocation();
//	}
	@Override
	public void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
	}
	@Override
	public void onPause() {
		super.onPause();
		myLocationOverlay.disableMyLocation();
	}
//	private Bitmap getImageWithPin(Bitmap bitmap){
//		View v = LayoutInflater.from(getActivity()).inflate(R.layout.image_with_pin_layout, null);
//		v.setLayoutParams(new LayoutParams(60,40));
//		((ImageView)v.findViewById(R.id.image)).setImageBitmap(bitmap);
//		
//		return getBitmapFromView(v);
//	}
//	private Bitmap getBitmapFromView(View view) {
//		Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),
//				view.getHeight(), Bitmap.Config.ARGB_8888);
//		Canvas canvas = new Canvas(returnedBitmap);
//		Drawable bgDrawable = view.getBackground();
//		if (bgDrawable != null)
//			bgDrawable.draw(canvas);
//		else
//			canvas.drawColor(Color.WHITE);
//		view.draw(canvas);
//		return returnedBitmap;
//	}
	
	private Bitmap getBitmapWithPin(Bitmap btmp) {
		Paint p = new Paint(); 
		p.setColor(Color.WHITE);
		Bitmap returnedBitmap = Bitmap.createBitmap(btmp.getWidth() + 20, btmp.getHeight() + 70, Bitmap.Config.ARGB_8888);
		
		Rect dest = new Rect(10, 60, btmp.getWidth() - 10, btmp.getHeight() - 10);
		
		Canvas canvas = new Canvas(returnedBitmap);
		canvas.drawRect(new Rect(0, 50, canvas.getWidth() - 10, canvas.getHeight()), p);
		p.setColor(Color.BLACK);
		canvas.drawRect(dest, p);
		canvas.drawBitmap(btmp, null, dest, p);
		Bitmap pin = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
		Rect pinRect = new Rect(canvas.getWidth()/2 - pin.getWidth()/2, 0, canvas.getWidth()/2 + pin.getWidth()/2, pin.getHeight());
		canvas.drawBitmap(pin, null, pinRect, p);
		return returnedBitmap;
	}
}