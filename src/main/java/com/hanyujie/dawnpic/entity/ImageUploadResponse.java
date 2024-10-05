package com.hanyujie.dawnpic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ImageUploadResponse {
    private List<String> uuids;
}
