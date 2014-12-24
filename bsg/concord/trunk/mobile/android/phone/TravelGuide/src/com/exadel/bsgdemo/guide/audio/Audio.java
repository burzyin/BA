package com.exadel.bsgdemo.guide.audio;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:59 AM 12/4/13
 */

@SuppressWarnings("UnusedDeclaration")
public class Audio {
    public enum ContentType {
        Html, Picture
    }

    private String Path;
    private String Title;

    private ContentType contentType;
    private String content;

    public Audio(String path, String title, ContentType contentType, String content) {
        Path = path;
        Title = title;
        this.contentType = contentType;
        this.content = content;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
