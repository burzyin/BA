package com.exadel.bsgdemo.newroute;

import com.exadel.bsgdemo.data.Place;

import java.util.ArrayList;
import java.util.List;

public class RouteManager {
    private List<Place> mCurrentRoute;
    private static RouteManager sInstance;

    public static RouteManager getInstance() {
        if (sInstance == null)
            sInstance = new RouteManager();
        return sInstance;
    }

    private RouteManager() {
        mCurrentRoute = new ArrayList<Place>();
    }

    public boolean addPointToCurRoute(Place p) {
        return mCurrentRoute.add(p);
    }

    public void resetRoute() {
        mCurrentRoute.clear();
    }

    public List<Place> getRoute() {
        return mCurrentRoute;
    }

    public int getRouteItemsCount() {
        return mCurrentRoute.size();
    }

    public boolean contains(Place p) {
        return mCurrentRoute.contains(p);
    }

    public void removeItem(Place p) {
        mCurrentRoute.remove(p);
    }
}