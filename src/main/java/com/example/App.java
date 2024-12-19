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
            String modelPath = "/src/model";//location of the saved model (for tensorflow & pytorch)
            String inputImage = "src/resources/potato1.jpg";
            
            List<String> classNames = Arrays.asList("Bean", "Carrot", "Potato", "Tomato");//loaded model classes
            

            Object result = TaskHandler.handleTask(task, framework, inputImage, modelPath,classNames);
            System.out.println(result);
           
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
