package com.exadel.belarusattractions.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:24 AM, 3/14/13
 */

@Entity
@Table(name = "Photo")
public class Photo implements Serializable {
    private Long id;
    private String url;
    private String title;
    private PointOfInterest pointOfInterest;

    public Photo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id", unique = true, nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "photo_url", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "photo_title", nullable = true, insertable = true, updatable = true, length = 128, precision = 0)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    @JoinColumn(name = "poi_id", nullable = false)
    public PointOfInterest getPointOfInterest() {
        return pointOfInterest;
    }

    public void setPointOfInterest(PointOfInterest pointOfInterest) {
        this.pointOfInterest = pointOfInterest;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(url)
                .append(title)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Photo) {
            final Photo other = (Photo) obj;
            return new EqualsBuilder()
                    .append(this.getUrl(), other.getUrl())
                    .append(this.getTitle(), other.getTitle())
                    .isEquals();
        }
        return false;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
