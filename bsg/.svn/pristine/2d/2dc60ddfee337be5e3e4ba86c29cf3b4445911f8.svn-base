package com.exadel.belarusattractions.dao;

import com.exadel.belarusattractions.AbstractTransactionalTestClass;
import com.exadel.belarusattractions.dao.sight.SightDao;
import com.exadel.belarusattractions.dto.sights.Sight;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:50 AM, 2/18/13
 */

public class SightsDaoImplTest extends AbstractTransactionalTestClass {

    @Autowired
    @Qualifier("sightDao")
    private SightDao sightDao;

    @Test
    public void testGetSights() {
        List<Sight> sights = sightDao.getSights(new Locale("en"));
        Assert.assertNotNull(sights);
        for (Sight s : sights) {
            Assert.assertNotNull(s);
        }
    }

    @Test
    public void testGetSightById() {
        Sight sight = sightDao.getSightById(1L, new Locale("en"));
        Assert.assertNotNull(sight);
    }

    @Test()
    public void testGetSightByIdArgumentNull() {
        Sight sight = sightDao.getSightById(null, new Locale("en"));
        assertNull(sight);
    }

    @Test
    public void testGetSightByCode() throws Exception {
        Sight sight = sightDao.getSightByCode("national-library", new Locale("en"));
        Assert.assertNotNull(sight);
    }

    @Test
    public void testGetSelectedSights() {
        List<Sight> sightList = sightDao.getSelectedSights(new Long[]{1L, 2L, 3L}, new Locale("en"));
        Assert.assertNotNull(sightList);
        for (Sight s : sightList) {
            Assert.assertNotNull(s);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testGetSelectedSightsArgumentNull() {
        sightDao.getSelectedSights(null, new Locale("en"));
    }

    @Test
    public void testGetSelectedSightsWrongId() {
        assertTrue(sightDao.getSelectedSights(new Long[]{-1L}, new Locale("en")).size() == 0);
    }

}