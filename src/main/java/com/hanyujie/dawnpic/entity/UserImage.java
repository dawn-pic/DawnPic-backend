package com.hanyujie.dawnpic.entity;

import java.util.UUID;

public class UserImage {
    private Long id;
    private String username;
    private String password;
    private String role;

    private UUID imageUuid;
    private String name;
    private String extension;
    private String description;
    private String altInfo;
    private Long uploadDate;
    private int sizeWidth;
    private int sizeHeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "UserImage{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", imageUuid=" + imageUuid +
                ", name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", description='" + description + '\'' +
                ", altInfo='" + altInfo + '\'' +
                ", uploadDate=" + uploadDate +
                ", sizeWidth=" + sizeWidth +
                ", sizeHeight=" + sizeHeight +
                '}';
    }
}
