package com.example.task;

import java.io.IOException;

import ai.djl.ModelException;
import ai.djl.huggingface.translator.QuestionAnsweringTranslatorFactory;
import ai.djl.inference.Predictor;
import ai.djl.modality.nlp.qa.QAInput;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

public class QuestionAnswering {

  

    public static void main(String[] args) throws IOException, TranslateException, ModelException {
        String answer = QuestionAnswering.predict();
        System.out.println("Answer: " + answer);
        
    }

    public static String predict() throws IOException, TranslateException, ModelException {
        String question = "WHat happened to Hiroshima and Nagasaki?";
        String paragraph =
                "On 6 and 9 August 1945, the United States detonated two atomic bombs over Hiroshima and Nagasaki."
                + " The bombings killed between 150,000 and 246,000 people.";

        QAInput input = new QAInput(question, paragraph);
        System.out.println("Question: " + question);

        Criteria<QAInput, String> criteria =
                Criteria.builder()
                        .setTypes(QAInput.class, String.class)
                        .optModelUrls(
                                "djl://ai.djl.huggingface.pytorch/deepset/minilm-uncased-squad2")
                        .optEngine("PyTorch")
                        .optTranslatorFactory(new QuestionAnsweringTranslatorFactory())
                        .optProgress(new ProgressBar())
                        .build();
                  

        try (ZooModel<QAInput, String> model = criteria.loadModel();
                Predictor<QAInput, String> predictor = model.newPredictor()) {
                var prediction = predictor.predict(input);
            return prediction;
        }
    }   
    
}
