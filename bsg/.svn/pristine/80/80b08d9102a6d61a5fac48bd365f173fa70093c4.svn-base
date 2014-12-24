package com.exadel.belarusattractions.dao;

import com.exadel.belarusattractions.AbstractTestClass;
import com.exadel.belarusattractions.dto.routes.Route;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Developer: Paulau Aliaksandr
 * Created: 11:10 AM, 2/18/13
 */

public class RoutesDaoImplTest extends AbstractTestClass {

    @Autowired
    @Qualifier("routesDao")
    RoutesDao routesDao;

    @Test
    public void testGetRoutes() throws Exception {
        List<Route> routes = routesDao.getRoutes();
        for (Route route : routes) {
            assertNotNull(route);
        }
    }

    @Test
    public void testGetRouteById() throws Exception {
        Route route = routesDao.getRouteById(1L);
        assertNotNull(route);
    }

    @Test(expected = NullPointerException.class)
    public void testGetRouteByIdArgumentNull() throws Exception {
        routesDao.getRouteById(null);
    }

    @Test
    public void testGetRouteByName() throws Exception {
        Route route = routesDao.getRouteByName("library-babruysk");
        assertNotNull(route);
    }

    @Test
    public void testGetRouteByNameWrongName() throws Exception {
        Route route = routesDao.getRouteByName("WRONG_NAME");
        assertNull(route);
    }

    @Test
    public void testGetRouteByNameArgumentNull() throws Exception {
        Route route = routesDao.getRouteByName(null);
        assertNull(route);
    }

    @Test
    public void testGetSelectedRoutes() throws Exception {
        List<Route> routeList = routesDao.getSelectedRoutes(new Long[]{1L, 2L});
        assertNotNull(routeList);
        for (Route route : routeList) {
            assertNotNull(route);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testGetSelectedRoutesArgumentNull() throws Exception {
        routesDao.getSelectedRoutes(null);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetSelectedRoutesWrongId() throws Exception {
        List<Route> routeList = routesDao.getSelectedRoutes(new Long[]{});
        assertNotNull(routeList);
        routesDao.getSelectedRoutes(new Long[]{-1L});
    }

    @Test
    public void testGetRelatedRoutes() throws Exception {
        List<Route> relatedRoutes = routesDao.getRelatedRoutes(1L);
        assertNotNull(relatedRoutes);
    }

    @Test
    public void testGetRelatedRoutesArgumentNull() throws Exception {
        List<Route> relatedRoutes = routesDao.getRelatedRoutes(null);
        assertTrue(relatedRoutes.isEmpty());
    }
}
