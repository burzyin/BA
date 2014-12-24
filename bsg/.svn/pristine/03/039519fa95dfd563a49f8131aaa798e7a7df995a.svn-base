package com.exadel.bsgdemo.newroute;

import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.exadel.bsgdemo.BaseActivity;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.place.PlaceDescriptionActivity;
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
import org.osmdroid.views.overlay.*;

import java.util.ArrayList;
import java.util.List;

public class NewRouteActivity extends BaseActivity {
    private static final int DEFAULT_ZOOM = 12;

    private org.osmdroid.views.MapView mMap;
    private LinearLayout mTopSwitcher;
    private TextView mDistanceView;
    private MapController mMapController;
    private ItemizedIconOverlay<OverlayItem> mItemizedOverlay;
    private MyLocationOverlay mLocationOverlay;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActiveButton(R.id.new_route_button, R.drawable.tab5_a);

        mTopSwitcher = (LinearLayout) findViewById(R.id.top_switcher);
        mDistanceView = (TextView) findViewById(R.id.distance_text_view);

        mMap = (MapView) findViewById(R.id.open_map_view);
        mMap.setBuiltInZoomControls(true);
        mMap.getController().setZoom(15);
        mMap.getController().setCenter(new GeoPoint(53.923426, 27.614397));
        mMap.setMultiTouchControls(true);

        mLocationOverlay = new MyLocationOverlay(this, mMap);
        mLocationOverlay.enableMyLocation();
        mMap.getOverlays().add(mLocationOverlay);

        mMapController = mMap.getController();
        mMapController.setZoom(DEFAULT_ZOOM);

        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        mMap.getOverlays().add(myScaleBarOverlay);
//        if(mItemizedOverlay.size() > 0)
//            mMap.getController().setCenter(mItemizedOverlay.getItem(0).getPoint());
        invalidateRoute();
    }

    PathOverlay roadOverlay;

    public void invalidateRoute() {
        try {
            mMap.getOverlays().remove(roadOverlay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (RouteManager.getInstance().getRouteItemsCount() > 1) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    RoadManager roadManager = new OSRMRoadManager();
                    List<Place> places = RouteManager.getInstance().getRoute();

                    ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>(places.size());
                    for (Place place : places) {
                        waypoints.add(new GeoPoint(Double.valueOf(place.getLongitude()), Double.valueOf(place.getLatitude())));
                    }

                    final Road road = roadManager.getRoad(waypoints);
                    roadOverlay = RoadManager.buildRoadOverlay(road, mMap.getContext());
                    mMap.getOverlays().add(roadOverlay);

                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            mTopSwitcher.setVisibility(View.VISIBLE);
                            mDistanceView.setText("Distance:\n\t" + road.mLength);
                            mMap.invalidate();
                        }
                    });
                }
            }).start();
        } else {
            mTopSwitcher.setVisibility(View.GONE);
        }

        mMap.setMapListener(new MapListener() {

            @Override
            public boolean onZoom(ZoomEvent zoomEvent) {
                try {
                    mMap.getOverlays().remove(mItemizedOverlay);
                } catch (UnsupportedOperationException e) {
                    e.printStackTrace();
                }
                Drawable marker = getResources().getDrawable(android.R.drawable.star_big_on);
                int markerWidth = marker.getIntrinsicWidth();
                int markerHeight = marker.getIntrinsicHeight();
                marker.setBounds(0, markerHeight, markerWidth, 0);

                ResourceProxy resourceProxy = new DefaultResourceProxyImpl(NewRouteActivity.this);

                mItemizedOverlay = new org.osmdroid.views.overlay.ItemizedIconOverlay<OverlayItem>(new ArrayList<OverlayItem>(),
                        new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                            @Override
                            public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                                startActivity(new Intent(NewRouteActivity.this, PlaceDescriptionActivity.class));
                                return true;
                            }

                            @Override
                            public boolean onItemLongPress(final int index, final OverlayItem item) {
                                Toast.makeText(NewRouteActivity.this, item.getTitle() + " long click", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        }, resourceProxy);

                mMap.getOverlays().add(mItemizedOverlay);
                List<Place> places = RouteManager.getInstance().getRoute();
                if (places != null && places.size() > 1) {
                    for (Place place : places)
                        try {
                            OverlayItem nextItem = new OverlayItem(place.getTitle(), String.valueOf(place.getId()), new GeoPoint(Float.valueOf(place.getLongitude()), Float.valueOf(place.getLatitude())));
                            Drawable dr;
                            if (place.getIconPath() != null)
                                dr = new BitmapDrawable(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BSGDemo/" + place.getIconPath());
                            else
                                dr = getResources().getDrawable(R.drawable.botanic_garden);

                            int zoomLevel = zoomEvent.getZoomLevel();

                            Bitmap bitmap = getBitmapWithPin(((BitmapDrawable) dr).getBitmap());
                            try {
                                Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (zoomLevel * zoomLevel) / 3, (zoomLevel * zoomLevel) / 3, true));
                                nextItem.setMarker(d);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                            mItemizedOverlay.addItem(nextItem);
                        } catch (NullPointerException ex) {
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
        mMapController.setZoom(DEFAULT_ZOOM);
    }

    public void addButtonProcess(View view) {
        new EditRouteDialogFragment() {
            public void onDetach() {
                try {
                    invalidateRoute();
                    super.onDetach();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.show(getSupportFragmentManager(), "edit_route");
    }

    @Override
    public void onResume() {
        super.onResume();
        mLocationOverlay.enableMyLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationOverlay.disableMyLocation();
    }

    private Bitmap getBitmapWithPin(Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Bitmap returnedBitmap = Bitmap.createBitmap(bitmap.getWidth() + 20, bitmap.getHeight() + 70, Bitmap.Config.ARGB_8888);

        Rect rect = new Rect(10, 60, bitmap.getWidth() - 10, bitmap.getHeight() - 10);

        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawRect(new Rect(0, 50, canvas.getWidth() - 10, canvas.getHeight()), paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(rect, paint);
        canvas.drawBitmap(bitmap, null, rect, paint);
        Bitmap pin = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
        Rect pinRect = new Rect(canvas.getWidth() / 2 - pin.getWidth() / 2, 0, canvas.getWidth() / 2 + pin.getWidth() / 2, pin.getHeight());
        canvas.drawBitmap(pin, null, pinRect, paint);
        return returnedBitmap;
    }

    @Override
    protected int getLayout() {
        return R.layout.new_route_map_layout;
    }
}