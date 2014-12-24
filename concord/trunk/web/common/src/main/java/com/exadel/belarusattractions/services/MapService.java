package com.exadel.belarusattractions.services;

import com.exadel.belarusattractions.dto.map.properties.MapProperties;
import com.exadel.belarusattractions.dto.map.MapType;

/**
 * Map properties service.
 * Gives mapProperties depending on type.
 *
 * Developer: Yan Khonskiy
 * Created: 5:24 AM, 11/13/12
 */
public interface MapService {

    public MapProperties getMapProperties(MapType type);

    public MapProperties getMapProperties();

    public MapType getDefaultMapType();
}