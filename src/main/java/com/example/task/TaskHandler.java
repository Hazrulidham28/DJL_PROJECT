package com.example.task;

import java.util.List;

public class TaskHandler {
    
   
    public static Object handleTask(String task,String specific_task, String framework, String input, String modelPath, List<String> classNames,String text1, String text2, String inputTextMask) throws Exception {
        String lowercased = task.toLowerCase();
        String lowercased_specific = specific_task.toLowerCase();

        if("image_classificationtf".equals(lowercased)) {
            return new ImageClassificationTF().runTask(classNames, 224, input);
        } else if ("image_classification".equals(lowercased)) {
            return new ImageClassification().runTask(input);
        } else if ("object_detection".equals(lowercased)) {
            return new ObjectDetection().runTask(input);
        }else if ("text_classification".equals(lowercased)) {
            //specific task
            if ("text_similarity".equals(lowercased_specific)){
                return new TextEmbedding().runTask(text1,text2);
            }else if ("fill_mask".equals(lowercased_specific)) {
                return new FillMask().runTask(inputTextMask);
            } else {
                throw new IllegalArgumentException("Unsupported task: " + lowercased);
            }
        }
        else {
            throw new IllegalArgumentException("Unsupported task: " + lowercased);
        }

}
}
