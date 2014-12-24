package com.exadel.belarusattractions.service;

import com.exadel.belarusattractions.AbstractTransactionalTestClass;
import com.exadel.belarusattractions.dto.sights.Sight;
import com.exadel.belarusattractions.services.SightsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertNotNull;

/**
 * Tests sightsService.
 * <p/>
 * Developer: Paulau Aliaksandr
 * Created: 12:11 AM, 02/15/13
 */

public class SightsServiceTest extends AbstractTransactionalTestClass {

    @Autowired
    SightsService sightsService;

    @Test
    public void testGetSights() throws Exception {
        List<Sight> sights = sightsService.getSights(new Locale("en"));
        for (Sight sight : sights) {
            assertNotNull(sight);
        }
    }

    @Test
    public void testGetSightById() throws Exception {
        Sight sight = sightsService.getSightById(1L, new Locale("en"));
        assertNotNull(sight);
        assertNotNull(sight.getId());
        assertNotNull(sight.getLatitude());
        assertNotNull(sight.getLongitude());
        assertNotNull(sight.getName());
        assertNotNull(sight.getCode());
        assertNotNull(sight.getPictogramUrl());
        assertNotNull(sight.getShortDescription());
        assertNotNull(sight.getLongDescription());
    }

    @Test
    public void testGetSightByCode() throws Exception {
        Sight sight = sightsService.getSightByCode("national-library", new Locale("en"));
        assertNotNull(sight);
        assertNotNull(sight.getId());
        assertNotNull(sight.getLatitude());
        assertNotNull(sight.getLongitude());
        assertNotNull(sight.getName());
        assertNotNull(sight.getCode());
        assertNotNull(sight.getPictogramUrl());
        assertNotNull(sight.getShortDescription());
        assertNotNull(sight.getLongDescription());
    }

    @Test
    public void testGetSelectedSights() throws Exception {
        List<Sight> sights = sightsService.getSelectedSights(new Long[]{1L, 2L, 3L}, new Locale("en"));
        for (Sight sight : sights) {
            assertNotNull(sight);
        }

    }
}