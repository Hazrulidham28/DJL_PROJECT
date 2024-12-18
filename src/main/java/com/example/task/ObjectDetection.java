package com.example.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.util.HelperFunctions;

import ai.djl.Application;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

public class ObjectDetection {

        public Object runTask(String imgPath)throws IOException, ModelException, TranslateException{
            DetectedObjects detection = predict(imgPath);
            return detection;
        }

    public static DetectedObjects predict(String imgPath) throws IOException, ModelException, TranslateException {
        // Path to your input image
        
        String url_image = imgPath;
        Image img = HelperFunctions.loadImage(url_image);

        // Define criteria for loading the model
        Criteria<Image, DetectedObjects> criteria = Criteria.builder()
                .optApplication(Application.CV.OBJECT_DETECTION)
                .setTypes(Image.class, DetectedObjects.class)
                .optArgument("threshold", 0.5) // Confidence threshold
                .optEngine("PyTorch") // Use PyTorch as the engine
                .optProgress(new ProgressBar())
                .build();

        try (ZooModel<Image, DetectedObjects> model = criteria.loadModel();
             Predictor<Image, DetectedObjects> predictor = model.newPredictor()) {
            DetectedObjects detection = predictor.predict(img);
            saveBoundingBoxImage(img, detection); // Save image with bounding boxes
            return detection;
        }
    }

    //can be put in helper functions
    private static void saveBoundingBoxImage(Image img, DetectedObjects detection) throws IOException {
        Path outputDir = Paths.get("src/main/resources");
        Files.createDirectories(outputDir);

        img.drawBoundingBoxes(detection); // Draw bounding boxes on the image

        Path imagePath = outputDir.resolve("detected-football.png");
        img.save(Files.newOutputStream(imagePath), "png");
        System.out.println("Detected objects image saved to: " + imagePath);
    }
}

