package com.exadel.bsgdemo.near;

import android.app.Activity;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.place.PlaceDescriptionActivity;


import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import java.util.ArrayList;
import java.util.List;

public class OpenStreetMapFragment extends android.support.v4.app.Fragment {

    private ItemizedIconOverlay<OverlayItem> mItemizedOverlay;
    private MyLocationOverlay mLocationOverlay;
    private List<Place> mPlaces;
//    private Handler mHandler;

    public OpenStreetMapFragment(List<Place> mPlaces) {
        this.mPlaces = mPlaces;
//        mHandler = new Handler();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.open_map_layout, null);
        assert v != null;
        final MapView map = (MapView) v.findViewById(R.id.open_map_view);
        map.setBuiltInZoomControls(true);

        //map.getController().setZoom(15);
        map.getController().setCenter(new GeoPoint(53.923426, 27.614397));
        map.setMultiTouchControls(true);
        map.setUseDataConnection(false);
        map.setMapListener(new MapListener() {

            @Override
            public boolean onZoom(ZoomEvent zoomEvent) {
                try {
                    map.getOverlays().remove(mItemizedOverlay);
                } catch (UnsupportedOperationException e) {
                    e.printStackTrace();
                }
                Drawable marker = getResources().getDrawable(android.R.drawable.star_big_on);
                assert marker != null;
                int markerWidth = marker.getIntrinsicWidth();
                int markerHeight = marker.getIntrinsicHeight();
                marker.setBounds(0, markerHeight, markerWidth, 0);

                ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getActivity());

                mItemizedOverlay = new org.osmdroid.views.overlay.ItemizedIconOverlay<OverlayItem>(new ArrayList<OverlayItem>(),
                        new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                            @Override
                            public boolean onItemSingleTapUp(final int id, final OverlayItem item) {
                                Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class);
                                target.putExtra("id", (long) id);
                                startActivity(target);
                                return true;
                            }

                            @Override
                            public boolean onItemLongPress(final int index, final OverlayItem item) {
                                Toast.makeText(getActivity(), item.getTitle() + " long click", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        }, resourceProxy);

                map.getOverlays().add(mItemizedOverlay);
                if (mPlaces != null) {
                    for (Place place : mPlaces)
                        try {
                            OverlayItem nextItem = new OverlayItem(place.getTitle(), String.valueOf(place.getId()), new GeoPoint(Float.valueOf(place.getLongitude()), Float.valueOf(place.getLatitude())));
                            Drawable drawable;
                            if (place.getIconPath() != null) {
                                drawable = new BitmapDrawable(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BSGDemo/" + place.getIconPath());
                            } else {
                                drawable = getResources().getDrawable(R.drawable.botanic_garden);
                            }

                            int zoomLevel = zoomEvent.getZoomLevel();

                            assert drawable != null;
                            Bitmap bitmap = getBitmapWithPin(((BitmapDrawable) drawable).getBitmap());
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

        mLocationOverlay = new MyLocationOverlay(getActivity(), map);
        mLocationOverlay.enableMyLocation();
        map.getOverlays().add(mLocationOverlay);

        MapController myMapController = (MapController) map.getController();
        myMapController.setZoom(12);

        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(getActivity());
        map.getOverlays().add(myScaleBarOverlay);
        if (mItemizedOverlay.size() > 0)
            map.getController().setCenter(mItemizedOverlay.getItem(0).getPoint());

//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                RoadManager roadManager = new OSRMRoadManager();
//
//                ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
//                waypoints.add(new GeoPoint(53.923426, 27.614397));
//                waypoints.add(new GeoPoint(47.991075, 37.806499));
//                final Road road = roadManager.getRoad(waypoints);
//                PathOverlay roadOverlay = RoadManager.buildRoadOverlay(road, map.getContext());
//                map.getOverlays().add(roadOverlay);
//
//                ArrayList<ExtendedOverlayItem> roadItems = new ArrayList<ExtendedOverlayItem>();
//                ItemizedOverlayWithBubble<ExtendedOverlayItem> roadNodes = new ItemizedOverlayWithBubble<ExtendedOverlayItem>(getActivity(), roadItems, map);
//                map.getOverlays().add(roadNodes);
//
//                Drawable marker = getResources().getDrawable(R.drawable.pin2);
//                for (int i = 0; i < road.mNodes.size(); i++) {
//                    RoadNode node = road.mNodes.get(i);
//                    ExtendedOverlayItem nodeMarker = new ExtendedOverlayItem("Step " + i, "", node.mLocation, getActivity());
//                    nodeMarker.setMarkerHotspot(OverlayItem.HotspotPlace.CENTER);
//                    nodeMarker.setMarker(marker);
//                    roadNodes.addItem(nodeMarker);
//                }
//                mHandler.post(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        map.invalidate();
//                        Toast.makeText(getActivity(), "length - " + road.mLength, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        }).start();

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLocationOverlay.disableMyLocation();
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

//    private Bitmap getImageWithPin(Bitmap bitmap) {
//        View v = LayoutInflater.from(getActivity()).inflate(R.layout.image_with_pin_layout, null);
//        v.setLayoutParams(new ViewGroup.LayoutParams(60, 40));
//        ((ImageView) v.findViewById(R.id.image)).setImageBitmap(bitmap);
//
//        return getBitmapFromView(v);
//    }

//    private Bitmap getBitmapFromView(View view) {
//        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),
//                view.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(returnedBitmap);
//        Drawable bgDrawable = view.getBackground();
//        if (bgDrawable != null)
//            bgDrawable.draw(canvas);
//        else
//            canvas.drawColor(Color.WHITE);
//        view.draw(canvas);
//        return returnedBitmap;
//    }

    private Bitmap getBitmapWithPin(Bitmap bitmap) {
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        Bitmap returnedBitmap = Bitmap.createBitmap(bitmap.getWidth() + 20, bitmap.getHeight() + 70, Bitmap.Config.ARGB_8888);

        Rect dest = new Rect(10, 60, bitmap.getWidth() - 10, bitmap.getHeight() - 10);

        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawRect(new Rect(0, 50, canvas.getWidth() - 10, canvas.getHeight()), p);
        p.setColor(Color.BLACK);
        canvas.drawRect(dest, p);
        canvas.drawBitmap(bitmap, null, dest, p);
        Bitmap pin = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
        int rand = ((int) (Math.random() * 1000)) % canvas.getWidth();
        Rect pinRect = new Rect(rand - pin.getWidth() / 2, 0, rand + pin.getWidth() / 2, pin.getHeight());
        canvas.drawBitmap(pin, null, pinRect, p);
        return returnedBitmap;
    }
}