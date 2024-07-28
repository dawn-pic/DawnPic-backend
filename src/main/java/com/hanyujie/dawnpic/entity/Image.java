package com.hanyujie.dawnpic.entity;


public class Image {
    private int id;
    private String name;
    private String extension;
    private String description;
    private String altInfo;
    private Long uploadDate;
    private int sizeWidth;
    private int sizeHeight;

    public Image(int id, String name, String extension, String description, String altInfo, Long uploadDate, int sizeWidth, int sizeHeight) {
        this.id = id;
        this.name = name;
        this.extension = extension;
        this.description = description;
        this.altInfo = altInfo;
        this.uploadDate = uploadDate;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAltInfo() {
        return altInfo;
    }

    public void setAltInfo(String altInfo) {
        this.altInfo = altInfo;
    }

    public Long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Long uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(int sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public int getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(int sizeHeight) {
        this.sizeHeight = sizeHeight;
    }
}
