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
@Table(name = "Contact")
public class Contact implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private String fax;
    private String email;
    private String website;

    @Column(name = "contact_id", unique = true, nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "contact_name", nullable = true, insertable = true, updatable = true, length = 128, precision = 0)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "contact_phone", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "contact_fax", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "contact_email", nullable = true, insertable = true, updatable = true, length = 128, precision = 0)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "contact_website", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(phone)
                .append(fax)
                .append(phone)
                .append(email)
                .append(website)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contact) {
            final Contact other = (Contact) obj;
            return new EqualsBuilder()
                    .append(this.getName(), other.getName())
                    .append(this.getPhone(), other.getPhone())
                    .append(this.getFax(), other.getFax())
                    .append(this.getEmail(), other.getEmail())
                    .append(this.getWebsite(), other.getWebsite())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
