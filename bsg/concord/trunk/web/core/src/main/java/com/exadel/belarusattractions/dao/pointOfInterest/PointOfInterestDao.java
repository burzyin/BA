package com.exadel.belarusattractions.dao.pointOfInterest;


import com.exadel.belarusattractions.entity.PointOfInterest;

import java.util.List;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:03 PM, 2/21/13
 */

public interface PointOfInterestDao {

    public PointOfInterest get(Long id);

    public PointOfInterest get(String code);

    public void update(PointOfInterest object);

    public void save(PointOfInterest object);

    public void delete(PointOfInterest object);

    public void deleteById(Long id);

    public List<PointOfInterest> getList();

    public List<PointOfInterest> getList(String type);

    public int count();

}