//package com.exadel.bsgdemo.data;
//
//import java.util.ArrayList;
//
//import org.osmdroid.ResourceProxy;
//import org.osmdroid.api.IMapView;
//import org.osmdroid.views.overlay.ItemizedIconOverlay;
//import org.osmdroid.views.overlay.OverlayItem;
//
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Point;
//import android.graphics.RectF;
//
///**
// * @author etric
// */
//public class MyItemizedOverlay extends ItemizedIconOverlay<OverlayItem>
//{
//    private ArrayList<OverlayItem> mItemList = new ArrayList<OverlayItem>();
//
//    public MyItemizedOverlay(ArrayList<OverlayItem> pList,
//                             ItemizedIconOverlay.OnItemGestureListener<OverlayItem> pOnItemGestureListener, ResourceProxy pResourceProxy)
//    {
//        super(pList, pOnItemGestureListener, pResourceProxy);
//        mItemList = pList;
//    }
//
//    public void addOverlay(OverlayItem aOverlayItem)
//    {
//        mItemList.add(aOverlayItem);
//        populate();
//    }
//
//    public void removeOverlay(OverlayItem aOverlayItem)
//    {
//        mItemList.remove(aOverlayItem);
//        populate();
//    }
//
//    @Override
//    protected OverlayItem createItem(int i)
//    {
//        return mItemList.get(i);
//    }
//
//    @Override
//    public int size()
//    {
//        if (mItemList != null)
//            return mItemList.size();
//        else
//            return 0;
//    }
//
//    @Override
//    public boolean onSnapToItem(int arg0, int arg1, Point arg2, IMapView arg3)
//    {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    @Override
//    protected void onDrawItem(Canvas canvas, OverlayItem item, Point curScreenCoords) {
//        super.onDrawItem(canvas, item, curScreenCoords);
//    }
//}