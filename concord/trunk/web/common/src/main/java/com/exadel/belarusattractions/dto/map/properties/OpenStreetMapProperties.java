package com.exadel.belarusattractions.dto.map.properties;

import com.exadel.belarusattractions.common.AbstractUIObject;
import com.exadel.belarusattractions.dto.map.MapType;

/**
 * Open street map initial properties.
 * <p/>
 * Developer: Paulau Aliaksandr
 * Created: 3:05 AM, 11/23/12
 */
public class OpenStreetMapProperties extends AbstractUIObject implements MapProperties {

    private double initLatitude;
    private double initLongitude;
    private Integer initZoom;
    private String initMapType;
    private boolean initZoomOnDoubleClick;

    public OpenStreetMapProperties() {
    }

    public void setInitLatitude(double initLatitude) {
        this.initLatitude = initLatitude;
    }

    public double getInitLatitude() {
        return initLatitude;
    }

    public void setInitLongitude(double initLongitude) {
        this.initLongitude = initLongitude;
    }

    public double getInitLongitude() {
        return initLongitude;
    }

    public void setInitZoom(Integer initZoom) {
        this.initZoom = initZoom;
    }

    public Integer getInitZoom() {
        return initZoom;
    }

    public void setInitMapType(String initMapType) {
        this.initMapType = initMapType;
    }

    public String getInitMapType() {
        return initMapType;
    }

    public void setInitZoomOnDoubleClick(boolean initZoomOnDoubleClick) {
        this.initZoomOnDoubleClick = initZoomOnDoubleClick;
    }

    public boolean getInitZoomOnDoubleClick() {
        return initZoomOnDoubleClick;
    }

    @Override
    public MapType getType() {
        return MapType.OPEN_STREET_MAP;
    }
}