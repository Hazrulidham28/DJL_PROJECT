package com.example.util;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.example.model.TaskInputs;

public class InputHandler {

    public static TaskInputs getUserInputs() {
        Scanner scanner = new Scanner(System.in);



        // Task selection
        System.out.println("Select the task:");
        System.out.println("1. Image Classification (Model Zoo)");
        System.out.println("2. Image Classification (TensorFlow)");
        System.out.println("3. Object Detection");
        System.out.println("4. Text Embedding");
        int taskChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        String task = switch (taskChoice) {
            case 1 -> "image_classification";
            case 2 -> "image_classificationtf";
            case 3 -> "object_detection";
            case 4 -> "text_embedding";
            default -> throw new IllegalArgumentException("Invalid task choice");
        };

        // Framework selection
        System.out.println("Select the framework:");
        System.out.println("1. TensorFlow");
        System.out.println("2. PyTorch");
        System.out.println("3. ModelZoo");
        int frameworkChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        String framework = switch (frameworkChoice) {
            case 1 -> "TensorFlow";
            case 2 -> "PyTorch";
            case 3 -> "modelzoo";
            default -> throw new IllegalArgumentException("Invalid framework choice");
        };

        String specificTask = "";
        if (task.equals("text_embedding")) {
            System.out.println("Select the specific task:");
            System.out.println("1. Text Similarity");
            System.out.println("2. Fill Mask");
            int specificTaskChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            specificTask = switch (specificTaskChoice) {
                case 1 -> "text_similarity";
                case 2 -> "fill_mask";
                default -> throw new IllegalArgumentException("Invalid specific task choice");
            };
        }

        String modelPath = "";
        if (framework.equals("TensorFlow") || framework.equals("PyTorch") && !task.equals("text_embedding")) {
            System.out.println("Enter the model path (e.g., src/resources/model.savedmodel):");
            modelPath = scanner.nextLine();
        }

        String inputImage = "";
        List<String> classNames = null;
        if (task.equals("image_classification") || task.equals("image_classificationtf")) {
            System.out.println("Enter the input image path (e.g., src/resources/potato1.jpg):");
            inputImage = scanner.nextLine();

            if (task.equals("image_classificationtf")) {
                System.out.println("Enter class names (comma-separated, e.g., Bean, Carrot, Potato, Tomato):");
                String classNamesInput = scanner.nextLine();
                classNames = Arrays.asList(classNamesInput.split(",\\s*"));
            }
        }

        // Text task inputs
        String text1 = "", text2 = "", inputTextMask = "";
        if (task.equals("text_embedding")) {
            if (specificTask.equals("text_similarity")) {
                System.out.println("Enter the first text for similarity:");
                text1 = scanner.nextLine();

                System.out.println("Enter the second text for similarity:");
                text2 = scanner.nextLine();
            } else if (specificTask.equals("fill_mask")) {
                System.out.println("Enter the input text with [MASK]:");
                inputTextMask = scanner.nextLine();
            }
        }

        scanner.close();

        return new TaskInputs(framework, task, specificTask, modelPath, inputImage, classNames, text1, text2, inputTextMask);
    }
}