package com.exadel.belarusattractions.dto.sights;

import com.exadel.belarusattractions.common.AbstractUIObject;

import java.util.List;

/**
 * Model. This object represents an attractive or interesting place for a tourist to visit.
 * A sight object contains information such as title, coordinates, short and detailed description.
 * New options will be added...
 *
 * Developer: Yan Khonskiy
 * Created: 1:37 AM, 11/9/12
 */
public class Sight extends AbstractUIObject {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String name;
    private String code;
    private String pictogramUrl;
    private String shortDescription;
    private String longDescription;
    private String address;
    private List<Photo> photos;

    public Sight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPictogramUrl() {
        return pictogramUrl;
    }

    public void setPictogramUrl(String pictogramUrl) {
        this.pictogramUrl = pictogramUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String toString() {
        return id + " " + "(" + latitude + ", " + longitude + ")" + "\n" + name + "\n" + shortDescription + "\n" ;
    }
}