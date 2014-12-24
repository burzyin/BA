package com.exadel.belarusattractions.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:33 AM, 2/19/13
 */

@Entity
@Table(name = "Point_of_Interest")
public class PointOfInterest implements Serializable {
    private Long id;
    private String type;
    private String code;
    private String pictogramUrl;
    private Double latitude;
    private Double longitude;
    private Map<String, Content> content = new HashMap<String, Content>();
    private List<Photo> photos = new ArrayList<Photo>();


    public PointOfInterest() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poi_id", unique = true, nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "poi_type", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "poi_code", nullable = true, insertable = true, updatable = true, length = 128, precision = 0)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "pictogram_url", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    public String getPictogramUrl() {
        return pictogramUrl;
    }

    public void setPictogramUrl(String pictogramUrl) {
        this.pictogramUrl = pictogramUrl;
    }

    @Column(name = "latitude", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "longitude", nullable = true, insertable = true, updatable = true, length = 22, precision = 0)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @OneToMany(targetEntity = Content.class, mappedBy = "pointOfInterest", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @MapKey(name = "locale")
    public Map<String, Content> getContent() {
        return content;
    }

    public void setContent(Map<String, Content> content) {
        this.content = content;
    }

    @OneToMany(targetEntity = Photo.class, mappedBy = "pointOfInterest", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(type)
                .append(code)
                .append(pictogramUrl)
                .append(latitude)
                .append(longitude)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PointOfInterest) {
            final PointOfInterest other = (PointOfInterest) obj;
            return new EqualsBuilder()
                    .append(this.getType(), other.getType())
                    .append(this.getCode(), other.getCode())
                    .append(this.getPictogramUrl(), other.getPictogramUrl())
                    .append(this.getLatitude(), other.getLatitude())
                    .append(this.getLongitude(), other.getLongitude())
                    .isEquals();
        }
        return false;
    }
}