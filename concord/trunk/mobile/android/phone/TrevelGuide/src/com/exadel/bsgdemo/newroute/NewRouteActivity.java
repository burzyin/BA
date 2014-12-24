package com.exadel.bsgdemo.newroute;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
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
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.place.PlaceDescriptionActivity;

public class NewRouteActivity extends FragmentActivity{
	private static int DEFAULT_ZOOM = 12;
	
	private org.osmdroid.views.MapView map;
	private LinearLayout top_switcher;
	private TextView distanceView;
	private MapController myMapController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_route_map_layout);
		top_switcher = (LinearLayout) findViewById(R.id.top_switcher);
		distanceView = (TextView) findViewById(R.id.distance_text_view);
		
		map = (MapView) findViewById(R.id.open_map_view);
		map.setBuiltInZoomControls(true);
		map.getController().setZoom(15);
		map.getController().setCenter(new GeoPoint(53.923426, 27.614397));
		map.setMultiTouchControls(true);
		
		myLocationOverlay = new org.osmdroid.views.overlay.MyLocationOverlay(this, map);
        myLocationOverlay.enableMyLocation();
        map.getOverlays().add(myLocationOverlay);

        myMapController = map.getController();
        myMapController.setZoom(DEFAULT_ZOOM);
        
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        map.getOverlays().add(myScaleBarOverlay);
//        if(myItemizedOverlay.size() > 0)
//            map.getController().setCenter(myItemizedOverlay.getItem(0).getPoint());
        invalidateRoute();
	}
	PathOverlay roadOverlay;
	public void invalidateRoute(){
		try{
			map.getOverlays().remove(roadOverlay);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(RouteManager.getInstance().getRouteItemsCount() > 1){
	        new Thread(new Runnable() {
				
				@Override
				public void run() {
					RoadManager roadManager = new OSRMRoadManager();
					List<Place> places = RouteManager.getInstance().getRoute();
					
					ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>(places.size());
			        for (int i = 0; i < places.size(); i++) {
						waypoints.add(new GeoPoint(Double.valueOf(places.get(i).getLongtitude()), Double.valueOf(places.get(i).getLatitude()))); 
					}
					
			        final Road road = roadManager.getRoad(waypoints);
			        roadOverlay = RoadManager.buildRoadOverlay(road, map.getContext());
			        map.getOverlays().add(roadOverlay);
			        
		        	h.post(new Runnable() {
						
						@Override
						public void run() {
							top_switcher.setVisibility(View.VISIBLE);
							distanceView.setText("Distance:\n\t" + road.mLength);
							map.invalidate();
						}
					});
				}
			}).start();
        }else{
        	top_switcher.setVisibility(View.GONE);
        }
		
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
		        
		        ResourceProxy resourceProxy = new DefaultResourceProxyImpl(NewRouteActivity.this);
		        
		        myItemizedOverlay = new org.osmdroid.views.overlay.ItemizedIconOverlay<OverlayItem>(new ArrayList<OverlayItem>(),
		                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>()
		                {
		                    @Override
		                    public boolean onItemSingleTapUp(final int index, final OverlayItem item){
		                    		startActivity(new Intent(NewRouteActivity.this, PlaceDescriptionActivity.class));
		                		return true; 
		                    }

		                    @Override
		                    public boolean onItemLongPress(final int index, final OverlayItem item)
		                    {
		                    	Toast.makeText(NewRouteActivity.this, item.getTitle() + " long click", Toast.LENGTH_SHORT).show();
		                        return true;
		                    }
		                }, resourceProxy);

		        map.getOverlays().add(myItemizedOverlay);
		        List<Place> places = RouteManager.getInstance().getRoute();
		        if(places != null && places.size() > 1){
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
		myMapController.setZoom(DEFAULT_ZOOM);
	}
	
	public void addButtonProcess(View v){
		new EditRouteDialogFragment(){
			public void onDetach() {
				try{
					invalidateRoute();
					super.onDetach();
				}catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.show(getSupportFragmentManager(), "edit_route");
	}
	public void backButtonProcess(View v){
		Toast.makeText(this, "Not suported yet...", Toast.LENGTH_SHORT).show();
	}
	
	ItemizedIconOverlay myItemizedOverlay;
	org.osmdroid.views.overlay.MyLocationOverlay myLocationOverlay;
	
	private Handler h = new Handler();

//	@Override
//	public void onResume() {
//		super.onResume();
//		myLocationOverlay.enableMyLocation();
//	}
//	@Override
//	public void onPause() {
//		super.onPause();
//		myLocationOverlay.disableMyLocation();
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