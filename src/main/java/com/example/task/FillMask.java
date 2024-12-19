package com.example.task;

import java.io.IOException;

import ai.djl.ModelException;
import ai.djl.huggingface.translator.FillMaskTranslatorFactory;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

public class FillMask {
    public static void main(String[] args) throws IOException, ModelException, TranslateException {
        String text = "Hello I'm a [MASK] model.";

        Criteria<String, Classifications> criteria =
                Criteria.builder()
                        .setTypes(String.class, Classifications.class)
                        .optModelUrls("djl://ai.djl.huggingface.pytorch/bert-base-cased")
                        .optEngine("PyTorch")
                        .optTranslatorFactory(new FillMaskTranslatorFactory())
                        .optProgress(new ProgressBar())
                        .build();

        try (ZooModel<String, Classifications> model = criteria.loadModel();
                Predictor<String, Classifications> predictor = model.newPredictor()) {
            Classifications res = predictor.predict(text);
            System.out.println(res);
        }
    }
}
