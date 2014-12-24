package com.exadel.belarusattractions.service;

import com.exadel.belarusattractions.dto.map.MapType;
import com.exadel.belarusattractions.dto.map.properties.GoogleMapProperties;
import com.exadel.belarusattractions.dto.map.properties.MapProperties;
import com.exadel.belarusattractions.dto.map.properties.OpenStreetMapProperties;
import com.exadel.belarusattractions.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * MapService.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 3:16 AM, 11/19/12
 */

public class MapServiceImpl implements MapService {

    @Autowired
    @Qualifier("googleMapProperties")
    private MapProperties defaultMapProperties;

    @Autowired
    @Qualifier("googleMapProperties")
    private MapProperties googleMapProperties;

    @Autowired
    @Qualifier("openStreetMapProperties")
    private MapProperties openStreetMapProperties;

    public void setGoogleMapProperties(GoogleMapProperties googleMapProperties) {
        this.googleMapProperties = googleMapProperties;
    }

    public void setOpenStreetMapProperties(OpenStreetMapProperties openStreetMapProperties) {
        this.openStreetMapProperties = openStreetMapProperties;
    }

    public void setDefaultMapProperties(MapProperties defaultMapProperties) {
        this.defaultMapProperties = defaultMapProperties;
    }

    @Override
    public MapProperties getMapProperties(MapType type) {

        switch (type) {

            case GOOGLE_MAP:
                return googleMapProperties;

             case OPEN_STREET_MAP:
                 return openStreetMapProperties;

             default:
                 return defaultMapProperties;
        }
    }

    @Override
    public MapProperties getMapProperties() {
        return defaultMapProperties;
    }

    @Override
    public MapType getDefaultMapType() {
        return defaultMapProperties.getType();
    }
}