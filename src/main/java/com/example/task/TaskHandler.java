package com.example.task;

import java.util.List;

public class TaskHandler {
    
    public static Object handleTask(String task, String framework, String input, String modelPath, List<String> classNames) throws Exception {
        String lowercased = task.toLowerCase();

        if (lowercased.equals("image_classificationTF")) {
            return new ImageClassificationTF().runTask(classNames, 224, input);
        } else if (lowercased.equals("image_classification")) {
            return new ImageClassification().runTask(input);
        } else if (lowercased.equals("object_detection")) {
            return new ObjectDetection().runTask(input);
        } else {
            throw new IllegalArgumentException("Unsupported task: " + lowercased);
        }

}
}
