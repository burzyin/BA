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
@Table(name = "Video")
public class Video implements Serializable {
    private Long id;
    private String url;
    private String code;
    private String type;
    private String title;

    @Id
    @Column(name = "video_id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "video_url", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "video_code", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "video_type", nullable = true, insertable = true, updatable = true, length = 5, precision = 0)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "video_title", nullable = true, insertable = true, updatable = true, length = 128, precision = 0)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Video) {
            final Video other = (Video) obj;
            return new EqualsBuilder()
                    .append(this.getUrl(), other.getUrl())
                    .append(this.getCode(), other.getCode())
                    .append(this.getType(), other.getType())
                    .append(this.getTitle(), other.getTitle())
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(url)
                .append(code)
                .append(type)
                .append(title)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
