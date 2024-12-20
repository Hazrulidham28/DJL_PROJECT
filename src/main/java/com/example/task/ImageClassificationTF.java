package com.example.task;

import java.util.List;

import com.example.model.ModelHandler;
import com.example.util.HelperFunctions;

import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.translate.Batchifier;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;

public class ImageClassificationTF implements Task {

    @Override
    public Object runTask(String specificTask, String framework, String input, String modelPath,List<String> classNames, String text1, String text2, String inputTextMask)throws Exception {
       
        Image img = HelperFunctions.loadImage(input);
        // var modelPath = "src/resources/model.savedmodel";
        
        //need to pass the classes and size of image
        MyTranslator translator = new MyTranslator(classNames,224);
        
        Predictor<Image, Classifications> predictor = ModelHandler.getInstance().loadModel(modelPath, translator, framework);

        return predictor.predict(img);
    }
}


//translator need to required parameter like list of classes, image size
class MyTranslator implements Translator<Image, Classifications> {
    //list of classes
    private final List<String> CLASSES;
    private final int imageSize;


    //need to define constructor
    public MyTranslator(List<String> classes, int imageSize) {
        this.CLASSES = classes;
        this.imageSize = imageSize;
    }

    @Override
    public NDList processInput(TranslatorContext ctx, Image input) {
        NDManager manager = ctx.getNDManager();
        NDArray array = input.toNDArray(manager, Image.Flag.COLOR);
        array = NDImageUtils.resize(array, imageSize).div(255.0f);
        return new NDList(array);
    }

    @Override
    public Classifications processOutput(TranslatorContext ctx, NDList list) {
        NDArray probabilities = list.singletonOrThrow();
        return new Classifications(CLASSES, probabilities);
    }

    @Override
    public Batchifier getBatchifier() {
        return Batchifier.STACK;
    }
}
