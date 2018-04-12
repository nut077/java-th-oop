package com.nutfreedom.util;


import java.net.URL;

public class FileImages {
    public static String getFileImages(String filename) {
        String imageIcon = "";
        URL resource = FileImages.class.getClassLoader().getResource(filename);
        if (resource != null) {
            imageIcon = resource.getFile();
        }
        return imageIcon;
    }
}
