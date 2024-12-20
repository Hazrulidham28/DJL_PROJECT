package com.example;

import com.example.model.TaskInputs;
import com.example.task.TaskHandler;
import com.example.util.InputHandler;

public class App {
    public static void main(String[] args) {
        try {
            // get the user's inputs using the InputHandler class
            TaskInputs inputs = InputHandler.getUserInputs();

            // pass the collectd inputs to the taskhandlerr
            Object result = TaskHandler.getInstance().handleTask(
                inputs.getTask(),
                inputs.getSpecificTask(),
                inputs.getFramework(),
                inputs.getInputImage(),
                inputs.getModelPath(),
                inputs.getClassNames(),
                inputs.getText1(),
                inputs.getText2(),
                inputs.getInputTextMask()
            );

            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
