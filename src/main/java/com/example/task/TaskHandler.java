package com.example.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskHandler {

    private static TaskHandler instance;
    private final Map<String,Task> taskMap = new HashMap();
    
    private TaskHandler(){

        taskMap.put("image_classificationtf", new ImageClassificationTF());
        taskMap.put("image_classification", new ImageClassification());
        taskMap.put("text_embedding", new TextEmbedding());
        taskMap.put("fill_mask", new FillMask());
        // taskMap.put("object_detection", new ObjectDetection());

    }

    public static TaskHandler getInstance(){
        if(instance == null){
            instance = new TaskHandler();
        }
        return instance;
    }


    public Object handleTask(String task,String specific_task, String framework, String input, String modelPath, List<String> classNames,String text1, String text2, String inputTextMask) throws Exception {
        String lowercasedTask = task.toLowerCase();
        String lowercasedSpecific = specific_task.toLowerCase();
        
        Task taskInstance;
        taskInstance = taskMap.get(lowercasedTask);
        if (lowercasedTask.equals("text_embedding")) {

            if(specific_task.equals("text_embedding")){
                taskInstance = taskMap.get(lowercasedSpecific);

            }else if(specific_task.equals("fill_mask")){
                taskInstance = taskMap.get(lowercasedSpecific);

            }
            
        } else{
            taskInstance = taskMap.get(lowercasedTask);
        }
        

        // if (taskInstance == null) {
        //     throw new IllegalArgumentException("Unsupported task: " + task);
        // }
        
        return taskInstance.runTask(specific_task, framework, input, modelPath, classNames, text1, text2, inputTextMask);

}
}
