package com.example.task;

import java.util.List;

public class TaskHandler {
    
   
    public static Object handleTask(String task, String framework, String input, String modelPath, List<String> classNames) throws Exception {
        String lowercased = task.toLowerCase();

        if("image_classificationtf".equals(lowercased)) {
            return new ImageClassificationTF().runTask(classNames, 224, input);
        } else if ("image_classification".equals(lowercased)) {
            return new ImageClassification().runTask(input);
        } else if ("object_detection".equals(lowercased)) {
            return new ObjectDetection().runTask(input);
        } else {
            throw new IllegalArgumentException("Unsupported task: " + lowercased);
        }

}
}
