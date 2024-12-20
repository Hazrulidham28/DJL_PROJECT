package com.example.task;

import java.util.List;

import ai.djl.huggingface.translator.FillMaskTranslatorFactory;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;

public class FillMask implements Task{

    @Override
    public Object runTask(String specificTask, String framework, String input, String modelPath,
            List<String> classNames, String text1, String text2, String inputTextMask) throws Exception {
                // TODO Auto-generated method stub
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
            Classifications res = predictor.predict(inputTextMask);
            System.out.println(res);
            return res;
        }
    }
}
