package com.exadel.belarusattractions.dao;

import com.exadel.belarusattractions.dto.routes.Route;

import java.util.List;

/**
 * RoutesDao.
 * </p>
 * Developer: Paulau Aliaksandr
 * Created: 6:32 PM, 12/29/12
 */

public interface RoutesDao {

    public List<Route> getRoutes();

    public Route getRouteById(Long id);

    public Route getRouteByName(String name);

    public List<Route> getSelectedRoutes(Long[] indices);

    public List<Route> getRelatedRoutes(Long id);
}
