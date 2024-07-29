package com.hanyujie.dawnpic.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ImageExtensionChecker {
    public static boolean isImageExtension(String fileExtension) {
        List<String> imageExtensions = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "tiff");
        return imageExtensions.contains(fileExtension);
    }

    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) return "";
        else return fileName.substring(dotIndex + 1).toLowerCase();
    }
}
