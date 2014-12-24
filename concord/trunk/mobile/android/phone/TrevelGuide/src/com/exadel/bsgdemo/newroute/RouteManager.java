package com.exadel.bsgdemo.newroute;

import java.util.ArrayList;
import java.util.List;

import com.exadel.bsgdemo.data.Place;

public class RouteManager {
	private List<Place> curRoute;
	private static RouteManager instance;
	public static RouteManager getInstance(){
		if(instance == null)
			instance = new RouteManager();
		return instance;
	}
	private RouteManager(){
		curRoute = new ArrayList<Place>();
	}
	public boolean addPointToCurRoute(Place p){
		return curRoute.add(p);
	}
	public void resetRoute(){
		curRoute.clear();
	}
	public List<Place> getRoute(){
		return curRoute;
	}
	public int getRouteItemsCount(){
		return curRoute.size();
	}
	public boolean cointains(Place p){
		return curRoute.contains(p);
	}
	public void removeItem(Place p){
		curRoute.remove(p);
	}
}