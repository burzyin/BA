package com.exadel.belarusattractions.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Developer: Paulau Aliaksandr
 * Created: 1:16 PM, 2/26/13
 */

@Entity
@Table(name = "Content")
public class Content implements Serializable {
    private Long id;
    private String locale;
    private String name;
    private String shortDescription;
    private String longDescription;
    private PointOfInterest pointOfInterest;

    public Content() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "content_id", unique = true, nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getContentId() {
        return id;
    }

    public void setContentId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "poi_id", nullable = false)
    public PointOfInterest getPointOfInterest() {
        return pointOfInterest;
    }

    public void setPointOfInterest(PointOfInterest pointOfInterest) {
        this.pointOfInterest = pointOfInterest;
    }

    @Column(name = "locale", nullable = true, insertable = true, updatable = true, length = 5, precision = 0)
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 128, precision = 0)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "short_description", nullable = true, insertable = true, updatable = true, length = 65535, precision = 0)
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Column(name = "long_description", nullable = true, insertable = true, updatable = true, length = 65535, precision = 0)
    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(locale)
                .append(name)
                .append(shortDescription)
                .append(longDescription)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Content) {
            final Content other = (Content) obj;
            return new EqualsBuilder()
                    .append(this.getLocale(), other.getLocale())
                    .append(this.getName(), other.getName())
                    .append(this.getShortDescription(), other.getShortDescription())
                    .append(this.getLongDescription(), other.getLongDescription())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}