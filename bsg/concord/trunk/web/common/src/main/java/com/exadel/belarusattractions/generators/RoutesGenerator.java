package com.exadel.belarusattractions.generators;

import com.exadel.belarusattractions.dto.routes.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstration object. Generates few routes for application.
 * </p>
 * Developer: Paulau Aliaksandr
 * Created: 5:47 PM, 12/29/12
 */

@Deprecated
public class RoutesGenerator {

    private List<Route> routes;

    public RoutesGenerator() {
        routes = new ArrayList<Route>();
        generateRoutes();
    }

    private void generateRoutes() {
        List<Long> wayPoints = new ArrayList<Long>();
        wayPoints.add(1L);
        wayPoints.add(6L);
        routes.add(createRoute(
                0L,
                "library-babruysk",
                "From National Library to Babruysk Fortress",
                "One of the most impressive routes",
                wayPoints,
                "1$"
        ));
        wayPoints = new ArrayList<Long>();
        wayPoints.add(1L);
        wayPoints.add(2L);
        wayPoints.add(3L);
        routes.add(createRoute(
                1L,
                "attractive-minsk",
                "Attractive Minsk",
                "The best Minsk sights",
                wayPoints,
                "1.99$"
        ));
        wayPoints = new ArrayList<Long>();
        wayPoints.add(5L);
        wayPoints.add(6L);
        routes.add(createRoute(
                2L,
                "belarus-fortresses",
                "Belarus Fortresses",
                "In this route you will see the most famous fortresses in Belarus",
                wayPoints,
                "2.99$"
        ));
    }

    private Route createRoute(Long id, String name, String title, String description, List<Long> wayPoints, String cost) {
        return new Route(id, name, title, description, wayPoints, cost);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public Route getRouteById (long id) {
        return routes.get((int) id);
    }

    public Route getRouteByName(String name) {
        Route foundRoute = null;
        for (Route route : routes) {
            if (route.getName().equals(name)) {
                foundRoute = route;
            }
        }
        return foundRoute;
    }

    public List<Route> getRelatedRoutes(Long id) {
        List<Route> foundRoutes = new ArrayList<Route>();
        for (Route route : routes) {
            if (route.getWayPoints().contains(id)) {
                foundRoutes.add(route);
            }
        }
        return foundRoutes;
    }
}
