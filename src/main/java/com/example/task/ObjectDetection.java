package com.example.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.model.ModelHandler;
import com.example.util.HelperFunctions;

import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.DetectedObjects;

public class ObjectDetection implements Task{

        @Override
        public Object runTask(String specificTask, String framework, String input, String modelPath,
                List<String> classNames, String text1, String text2, String inputTextMask) throws Exception {
                    String url_image = input;
                    Image img = HelperFunctions.loadImage(url_image);
        
                    Predictor<Image, DetectedObjects> predictor = ModelHandler.getInstance().loadObjectDetection("", "");
                    DetectedObjects detection = predictor.predict(img);
                    saveBoundingBoxImage(img, detection);
        
                    return detection;
        }



    //can be put in helper functions
    private static void saveBoundingBoxImage(Image img, DetectedObjects detection) throws IOException {
        Path outputDir = Paths.get("src/main/resources");
        Files.createDirectories(outputDir);

        img.drawBoundingBoxes(detection); // Draw bounding boxes on the image

        Path imagePath = outputDir.resolve("detected-image.png");
        img.save(Files.newOutputStream(imagePath), "png");
        System.out.println("Detected objects image saved to: " + imagePath);
    }



}

