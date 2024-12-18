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
            
            String framework = "tensorflow";//tensorflow or pytorch
            String task = "image_classificationTF";//image_classification, object_detection,text_embedding
            String modelPath = "/src/model";//location of the saved model 
            String inputImage = "src/resources/potato1.jpg";
            
            List<String> classNames = Arrays.asList("Bean", "Carrot", "Potato", "Tomato");//loaded model classes
            

            Object result = TaskHandler.handleTask(task, framework, inputImage, modelPath,classNames);
            System.out.println(result);
           
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
