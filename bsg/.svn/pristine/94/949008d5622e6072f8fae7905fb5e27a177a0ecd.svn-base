package com.exadel.belarusattractions.service;

import com.exadel.belarusattractions.dao.RoutesDao;
import com.exadel.belarusattractions.dto.routes.Route;
import com.exadel.belarusattractions.services.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Implementation of the RoutesService.
 * </p>
 * Developer: Paulau Aliaksandr
 * Created: 6:34 PM, 12/29/12
 */


public class RoutesServiceImpl implements RoutesService {

    @Autowired
    @Qualifier("routesDao")
    private RoutesDao routesDao;

    public void setRoutesDao(RoutesDao routesDao) {
        this.routesDao = routesDao;
    }

    public List<Route> getRoutes() {
        return routesDao.getRoutes();
    }

    public Route getRouteById(Long id) {
        return routesDao.getRouteById(id);
    }

    public Route getRouteByName(String name) {
        return routesDao.getRouteByName(name);
    }

    public List<Route> getSelectedRoutes(Long[] indices) {
        return routesDao.getSelectedRoutes(indices);
    }

    public List<Route> getRelatedRoutes(Long id) {
        return routesDao.getRelatedRoutes(id);
    }
}
