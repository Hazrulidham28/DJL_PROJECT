package com.example.task;

import java.io.IOException;

import com.example.util.HelperFunctions;

import ai.djl.Application;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

public class ImageClassification {
        //currently only recieved imagepath

        public Object runTask(String imgPath)throws IOException, ModelException, TranslateException{
            Classifications detection = predict(imgPath);
            return detection;
        }

        public static Classifications predict(String imgPath) throws IOException, ModelException, TranslateException {
        // Path to your input image
        String url_image = imgPath;
        Image img = HelperFunctions.loadImage(url_image);


        
        //this model is loaded from djl and available in model zoo
        Criteria<Image, Classifications> criteria =
                    Criteria.builder()
                            .optApplication(Application.CV.IMAGE_CLASSIFICATION)
                            .setTypes(Image.class, Classifications.class)
                            .optModelUrls("djl://ai.djl.pytorch/resnet")
                            .optEngine("PyTorch")
                            .optProgress(new ProgressBar())
                            .build();

        try (ZooModel<Image, Classifications> model = ModelZoo.loadModel(criteria);
             Predictor<Image, Classifications> predictor = model.newPredictor()) {
            
            return predictor.predict(img);
        }
    }

   
}