package com.exadel.belarusattractions.dao;

import com.exadel.belarusattractions.dto.routes.Route;
import com.exadel.belarusattractions.generators.RoutesGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the RoutesDao
 * </p>
 * Developer: Paulau Aliaksandr
 * Created: 6:35 PM, 12/29/12
 */

public class RoutesDaoImpl implements RoutesDao {

    @Qualifier("routesGenerator")
    @Autowired
    private RoutesGenerator routesGenerator;

    public void setRoutesGenerator(RoutesGenerator routesGenerator) {
        this.routesGenerator = routesGenerator;
    }

    public List<Route> getRoutes() {
        return routesGenerator.getRoutes();
    }

    public Route getRouteById(Long id) {
        return routesGenerator.getRouteById(id);
    }

    public Route getRouteByName(String name) {
        return routesGenerator.getRouteByName(name);
    }

    public List<Route> getSelectedRoutes(Long[] indices) {
        List<Route> res = new ArrayList<Route>();
        for (Long i : indices) {
            res.add(routesGenerator.getRouteById(i));
        }
        return res;
    }

    public List<Route> getRelatedRoutes(Long id) {
        return routesGenerator.getRelatedRoutes(id);
    }
}
