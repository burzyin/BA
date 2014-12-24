package com.exadel.belarusattractions.service;

import com.exadel.belarusattractions.AbstractTestClass;
import com.exadel.belarusattractions.dto.map.MapType;
import com.exadel.belarusattractions.dto.map.properties.MapProperties;
import com.exadel.belarusattractions.services.MapService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * MapServiceImpl Test.
 * <p/>
 * Developer: Paulau Aliaksandr
 * Created: 11:23 AM, 02/15/13
 */

public class MapServiceImplTest extends AbstractTestClass {

    @Autowired
    private MapService mapService;

    @Test
    public void testGetMapProperties() {
        MapProperties mapProperties = mapService.getMapProperties(MapType.GOOGLE_MAP);
        System.out.println(mapProperties);
        assertNotNull(mapProperties);

        mapProperties = mapService.getMapProperties(MapType.OPEN_STREET_MAP);
        System.out.println(mapProperties);
        assertNotNull(mapProperties);
    }

    @Test(expected = NullPointerException.class)
    public void testGetNullMapProperties() {
        mapService.getMapProperties(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongMapProperties() {
        mapService.getMapProperties(MapType.parse("WRONG MAP TYPE"));
    }
}
