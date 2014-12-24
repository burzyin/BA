package com.exadel.belarusattractions.service;

import com.exadel.belarusattractions.AbstractTestClass;
import com.exadel.belarusattractions.dto.routes.Route;
import com.exadel.belarusattractions.services.RoutesService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:30 AM, 2/18/13
 */

public class RoutesServiceImplTest extends AbstractTestClass {

    @Autowired
    @Qualifier("routesService")
    private RoutesService routesService;

    @Test
    public void testGetRoutes() throws Exception {
        List<Route> routes = routesService.getRoutes();
        for (Route route : routes) {
            assertNotNull(route);
        }
    }

    @Test
    public void testGetRouteById() throws Exception {
        int size = routesService.getRoutes().size();
        for (int i = 0; i < size; i++) {
            Route route = routesService.getRouteById((long) i);
            assertNotNull(route);
            assertNotNull(route.getId());
            assertNotNull(route.getName());
            assertNotNull(route.getTitle());
            assertNotNull(route.getDescription());
            assertNotNull(route.getCost());
            assertNotNull(route.getWayPoints());
        }
    }
}