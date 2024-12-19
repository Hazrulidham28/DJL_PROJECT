package com.example.task;

import java.util.List;

public interface Task {
    Object runTask(String specificTask, String framework, String input, String modelPath, List<String> classNames, String text1, String text2, String inputTextMask) throws Exception;
}
