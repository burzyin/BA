package com.exadel.belarusattractions.service;

import com.exadel.belarusattractions.AbstractTransactionalTestClass;
import com.exadel.belarusattractions.entity.PointOfInterest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:49 PM, 2/25/13
 */

public class PointOfInterestServiceImplTest extends AbstractTransactionalTestClass {

    @Autowired
    @Qualifier("pointOfInterestService")
    private PointOfInterestService pointOfInterestService;

    @Test
    public void testSave() throws Exception {
        PointOfInterest pointOfInterest = new PointOfInterest();
        pointOfInterest.setCode("TEST_CODE");
        pointOfInterest.setLatitude(1234.5678);
        pointOfInterest.setLongitude(1234.5678);
        pointOfInterest.setPictogramUrl("TEST_URL");
        pointOfInterest.setId(1234L);
        pointOfInterest.setType("test");

        pointOfInterestService.save(pointOfInterest);
    }

    @Test
    public void testGet() throws Exception {
        PointOfInterest pointOfInterest = pointOfInterestService.get(1L);
        assertNotNull(pointOfInterest);
        assertNotNull(pointOfInterest.getType());
        assertNotNull(pointOfInterest.getCode());
        assertNotNull(pointOfInterest.getPictogramUrl());
        assertNotNull(pointOfInterest.getLatitude());
        assertNotNull(pointOfInterest.getLongitude());

        pointOfInterest = pointOfInterestService.get("national-library");
        assertNotNull(pointOfInterest);
        assertNotNull(pointOfInterest.getType());
        assertNotNull(pointOfInterest.getCode());
        assertNotNull(pointOfInterest.getPictogramUrl());
        assertNotNull(pointOfInterest.getLatitude());
        assertNotNull(pointOfInterest.getLongitude());

    }

    @Test(expected = NullPointerException.class)
    public void testGetWrongId() throws Exception {
        pointOfInterestService.get(-1L);
    }

    @Test()
    public void testUpdate() throws Exception {
        PointOfInterest pointOfInterest = pointOfInterestService.get(1L);
        pointOfInterest.setType("TEST");
        pointOfInterestService.update(pointOfInterest);
    }

    @Test
    public void testDelete() throws Exception {
        PointOfInterest pointOfInterest = pointOfInterestService.get(1L);
        pointOfInterestService.delete(pointOfInterest);
    }

    @Test
    public void testDeleteById() throws Exception {
        pointOfInterestService.deleteById(1L);
    }

    @Test
    public void testGetList() throws Exception {
        List<PointOfInterest> pointOfInterestList = pointOfInterestService.getList();
        for (PointOfInterest pointOfInterest : pointOfInterestList) {
            assertNotNull(pointOfInterest);
        }

        pointOfInterestList = pointOfInterestService.getList("sight");
        for (PointOfInterest pointOfInterest : pointOfInterestList) {
            assertNotNull(pointOfInterest);
        }
    }

    @Test
    public void testCount() throws Exception {
        int count = pointOfInterestService.count();
        assertNotNull(count);
    }
}