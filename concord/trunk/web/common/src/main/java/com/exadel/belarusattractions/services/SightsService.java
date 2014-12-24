package com.exadel.belarusattractions.services;

import com.exadel.belarusattractions.dto.sights.Sight;

import java.util.List;
import java.util.Locale;

/**
 * Sights service.
 *
 * Developer: Yan Khonskiy
 * Created: 11:41 AM, 11/9/12
 */
public interface SightsService {

    public List<Sight> getSights(Locale locale);

    public Sight getSightById(Long id, Locale locale);

    public Sight getSightByCode(String name, Locale locale);

    public List<Sight> getSelectedSights(Long [] indices, Locale locale);

}