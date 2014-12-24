package com.exadel.belarusattractions.service;

import com.exadel.belarusattractions.dao.pointOfInterest.PointOfInterestDao;
import com.exadel.belarusattractions.entity.PointOfInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:08 PM, 2/21/13
 */

@Service("pointOfInterestService")
public class PointOfInterestServiceImpl implements PointOfInterestService {

    @Autowired
    @Qualifier("pointOfInterestDao")
    private PointOfInterestDao dao;

    public void setDao(PointOfInterestDao dao) {
        this.dao = dao;
    }

    public void save(PointOfInterest pointOfInterest) {
        dao.save(pointOfInterest);
    }

    @Override
    public PointOfInterest get(Long id) {
        PointOfInterest pointOfInterest = dao.get(id);
        pointOfInterest.getContent().size();
        return pointOfInterest;
    }

    @Override
    public PointOfInterest get(String code) {
        return dao.get(code);
    }

    @Override
    public void update(PointOfInterest pointOfInterest) {
        dao.update(pointOfInterest);
    }

    @Override
    public void delete(PointOfInterest pointOfInterest) {
        dao.delete(pointOfInterest);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<PointOfInterest> getList() {
        return dao.getList();
    }

    @Override
    public List<PointOfInterest> getList(String type) {
        return dao.getList(type);
    }

    @Override
    public int count() {
        return dao.count();
    }
}

