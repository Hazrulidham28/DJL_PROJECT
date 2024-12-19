package com.example;

import java.util.Arrays;
import java.util.List;

import com.example.task.TaskHandler;

public class App 
{
    public static void main( String[] args )
    {
        try {
            //
            
            String framework = "tensorflow";//tensorflow or pytorch or modelzoo
            String task = "image_classificationtf";//image_classification (modelzoo) || image_classificationtf (load model), object_detection,text_embedding
            String specific_task = "fill_mask";//text_similarity , fill_mask
            
            //for image classification
            String modelPath = "/src/model";//location of the saved model (for tensorflow & pytorch)
            String inputImage = "src/resources/potato1.jpg";
            List<String> classNames = Arrays.asList("Bean", "Carrot", "Potato", "Tomato");//loaded model classes

            //for text classification
            //For similarity search
            String text1 ="Alif love machine learning and AI",text2 = "Alif enjoy sleeping and always letih";
            
            // for fill mask
            String inputTextMask ="Machine [MASK] is the best model.";

            Object result = TaskHandler.handleTask(task,specific_task, framework, inputImage, modelPath,classNames,text1,text2,inputTextMask);
            System.out.println(result);
           
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
