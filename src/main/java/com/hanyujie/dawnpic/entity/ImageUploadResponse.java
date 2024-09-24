package com.hanyujie.dawnpic.entity;


public class ImageUploadResponse {
    private String uuid;

    public ImageUploadResponse(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
