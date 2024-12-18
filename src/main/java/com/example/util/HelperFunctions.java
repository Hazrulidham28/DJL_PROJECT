package com.example.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;

public class HelperFunctions {
    //load input image from path

    public static Image loadImage(String imagePath) throws IOException{
        Path imageFile = Paths.get(imagePath);
        Image img = ImageFactory.getInstance().fromFile(imageFile);
        return img;
    }

    // add other method as well
}
