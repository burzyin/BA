package com.exadel.belarusattractions.dto.map.properties;

import com.exadel.belarusattractions.common.AbstractUIObject;
import com.exadel.belarusattractions.dto.map.MapType;

/**
 * Google map initial properties.
 * Initial coordinates (Minsk Belarus).
 * Initial initZoom (to see all the map of Belarus).
 * Key enables to use google map service.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 5:25 AM, 11/13/12
 */
public class GoogleMapProperties extends AbstractUIObject implements MapProperties {

    private double initLatitude;
    private double initLongitude;
    private Integer initZoom;
    private String key;

    public GoogleMapProperties() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getInitLatitude() {
        return initLatitude;
    }

    public void setInitLatitude(double initLatitude) {
        this.initLatitude = initLatitude;
    }

    public double getInitLongitude() {
        return initLongitude;
    }

    public void setInitLongitude(double initLongitude) {
        this.initLongitude = initLongitude;
    }

    public Integer getInitZoom() {
        return initZoom;
    }

    public void setInitZoom(Integer initZoom) {
        this.initZoom = initZoom;
    }

    @Override
    public MapType getType() {
        return MapType.GOOGLE_MAP;
    }
}