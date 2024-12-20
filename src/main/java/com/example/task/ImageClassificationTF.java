package com.example.task;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.example.util.HelperFunctions;

import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.Batchifier;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;

public class ImageClassificationTF {

    public Object runTask(List<String> classes,int imageSize,String imgPath) throws IOException, ModelNotFoundException, MalformedModelException, TranslateException {
       
        
        String url_image = imgPath;
        Image img = HelperFunctions.loadImage(url_image);
        var modelPath = "src/resources/model.savedmodel";
        
        //need to pass the classes and size of image
        MyTranslator translator = new MyTranslator(classes,imageSize);
        
        Criteria<Image , Classifications> criteria = Criteria.builder()
                .setTypes(Image.class, Classifications.class)
                .optModelPath(Paths.get(modelPath))
                .optTranslator(translator)
                .optProgress(new ProgressBar())
                .optEngine("TensorFlow")//if the model are tensorflow
                .build();
        ZooModel model = criteria.loadModel();
        Predictor< Image, Classifications> predictor = model.newPredictor();
        Classifications classifications = predictor.predict(img);

        return classifications;
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
