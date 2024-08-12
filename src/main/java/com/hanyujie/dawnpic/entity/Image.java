package com.hanyujie.dawnpic.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.UUID;

public class Image {
    @TableId
    private UUID imageUuid;
    private String name;
    private String extension;
    private String description;
    private String altInfo;
    private Long uploadDate;
    private int sizeWidth;
    private int sizeHeight;

    public Image(UUID imageUuid, String name, String extension, String description, String altInfo, Long uploadDate, int sizeWidth, int sizeHeight) {
        this.imageUuid = imageUuid;
        this.name = name;
        this.extension = extension;
        this.description = description;
        this.altInfo = altInfo;
        this.uploadDate = uploadDate;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
    }

    public UUID getImageUuid() {
        return imageUuid;
    }

    public void setImageUuid(UUID imageUuid) {
        this.imageUuid = imageUuid;
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
