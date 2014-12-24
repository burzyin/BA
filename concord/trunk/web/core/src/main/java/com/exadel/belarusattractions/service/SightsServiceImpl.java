package com.exadel.belarusattractions.service;

import com.exadel.belarusattractions.dao.sight.SightDao;
import com.exadel.belarusattractions.dto.sights.Sight;
import com.exadel.belarusattractions.services.SightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * Sights service.
 * <p/>
 * Developer: Paulau Aliaksandr
 * Created: 1:55 AM, 11/9/12
 */

@Service("sightsService")
public class SightsServiceImpl implements SightsService {

    @Autowired
    @Qualifier("sightDao")
    private SightDao dao;

    public void setDao(SightDao dao) {
        this.dao = dao;
    }

    public List<Sight> getSights(Locale locale) {
        return dao.getSights(locale);
    }

    public Sight getSightById(Long id, Locale locale) {
        return dao.getSightById(id, locale);
    }

    public Sight getSightByCode(String code, Locale locale) {
        return dao.getSightByCode(code, locale);
    }

    public List<Sight> getSelectedSights(Long[] indices, Locale locale) {
        return dao.getSelectedSights(indices, locale);
    }
}