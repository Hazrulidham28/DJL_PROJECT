package com.example.model;

import java.nio.file.Paths;

import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.Translator;

//import djl model

public class ModelHandler {


    private static ModelHandler instance;

    private ModelHandler(){}

    public static ModelHandler getInstance() {
        if (instance == null) {
            instance = new ModelHandler();
        }
        return instance;
    }


    //method load tensorflow model here
    public Predictor<Image, Classifications> loadModel(String modelPath, Translator <Image, Classifications> translator, String framework) throws Exception {
        Criteria<Image, Classifications> criteria = Criteria.builder()
                .setTypes(Image.class, Classifications.class)
                .optModelPath(Paths.get(modelPath))
                .optTranslator(translator)
                .optProgress(new ProgressBar())
                .optEngine(framework)
                .build();

        ZooModel<Image, Classifications> model = criteria.loadModel();
        return model.newPredictor();
    }    

}
