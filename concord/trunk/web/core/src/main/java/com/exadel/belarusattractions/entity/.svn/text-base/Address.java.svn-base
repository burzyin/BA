package com.exadel.belarusattractions.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:32 AM, 3/14/13
 */

@Entity
@Table(name = "Address")
public class Address implements Serializable {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String building;

    @Id
    @Column(name = "address_id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "country", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "city", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "street", nullable = true, insertable = true, updatable = true, length = 128, precision = 0)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "building", nullable = true, insertable = true, updatable = true, length = 8, precision = 0)
    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Address) {
            final Address other = (Address) obj;
            return new EqualsBuilder()
                    .append(this.getCountry(), other.getCountry())
                    .append(this.getCity(), other.getCity())
                    .append(this.getStreet(), other.getStreet())
                    .append(this.getBuilding(), other.getBuilding())
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(country)
                .append(city)
                .append(street)
                .append(building)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
