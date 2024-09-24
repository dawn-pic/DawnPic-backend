package com.hanyujie.dawnpic.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class UserImage {
    private String username;
    private UUID imageUuid;
    private String name;
    private String extension;
    private String description;
    private String altInfo;
    private Long uploadDate;
    private int sizeWidth;
    private int sizeHeight;
}
