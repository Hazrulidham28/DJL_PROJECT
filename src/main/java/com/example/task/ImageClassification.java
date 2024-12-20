package com.example.task;

import java.util.List;

import com.example.model.ModelHandler;
import com.example.util.HelperFunctions;

import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;

public class ImageClassification implements Task{

        @Override
        public Object runTask(String specificTask, String framework, String input, String modelPath,
                List<String> classNames, String text1, String text2, String inputTextMask) throws Exception {
        
            Image img = HelperFunctions.loadImage(input);


            Predictor<Image, Classifications> predictor = ModelHandler.getInstance().loadModelZoo("djl://ai.djl.pytorch/resnet", "PyTorch");
    
            return predictor.predict(img);
        }

}

   
