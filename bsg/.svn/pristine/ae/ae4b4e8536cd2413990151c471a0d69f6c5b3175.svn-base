package com.exadel.bsgdemo.newroute;

import com.exadel.bsgdemo.data.Place;

import java.util.ArrayList;
import java.util.List;

public class RouteManager {
    private List<Place> mCurrentRoute;
    private static RouteManager s_instance;

    public static RouteManager getInstance() {
        if (s_instance == null)
            s_instance = new RouteManager();
        return s_instance;
    }

    private RouteManager() {
        mCurrentRoute = new ArrayList<Place>();
    }

    public boolean addPointToCurRoute(Place p) {
        return mCurrentRoute.add(p);
    }

    @SuppressWarnings("UnusedDeclaration")
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