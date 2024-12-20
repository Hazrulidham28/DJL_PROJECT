package com.example.model;

import java.util.List;

public class TaskInputs {
    private final String framework;
    private final String task;
    private final String specificTask;
    private final String modelPath;
    private final String inputImage;
    private final List<String> classNames;
    private final String text1;
    private final String text2;
    private final String inputTextMask;

    public TaskInputs(String framework, String task, String specificTask, String modelPath, String inputImage,
                      List<String> classNames, String text1, String text2, String inputTextMask) {
        this.framework = framework;
        this.task = task;
        this.specificTask = specificTask;
        this.modelPath = modelPath;
        this.inputImage = inputImage;
        this.classNames = classNames;
        this.text1 = text1;
        this.text2 = text2;
        this.inputTextMask = inputTextMask;
    }

    public String getFramework() {
        return framework;
    }

    public String getTask() {
        return task;
    }

    public String getSpecificTask() {
        return specificTask;
    }

    public String getModelPath() {
        return modelPath;
    }

    public String getInputImage() {
        return inputImage;
    }

    public List<String> getClassNames() {
        return classNames;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getInputTextMask() {
        return inputTextMask;
    }
}