package com.exadel.belarusattractions.dao.sight;

import com.exadel.belarusattractions.dto.sights.Sight;

import java.util.List;
import java.util.Locale;

/**
 * SightDao.
 * <p/>
 * Developer: Paulau Aliaksandr
 * Created: 2:20 PM, 3/11/13
 */

public interface SightDao {

    public List<Sight> getSights(Locale locale);

    public Sight getSightById(Long id, Locale locale);

    public Sight getSightByCode(String code, Locale locale);

    public List<Sight> getSelectedSights(Long[] indices, Locale locale);

}