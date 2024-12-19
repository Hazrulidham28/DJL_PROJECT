package com.example.task;

import java.io.IOException;

import ai.djl.ModelException;
import ai.djl.huggingface.translator.TextEmbeddingTranslatorFactory;
import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

public class TextEmbedding {

        //text embedding
        //also can use jinaa embedding model as well
        // djl://ai.djl.huggingface.pytorch/jinaai/jina-embeddings-v2-small-en

    public Object runTask(String text1, String text2) throws IOException, ModelException, TranslateException {

        Criteria<String, float[]> criteria =
                Criteria.builder()
                        .setTypes(String.class, float[].class)
                        .optModelUrls(
                                "djl://ai.djl.huggingface.pytorch/sentence-transformers/all-MiniLM-L6-v2")
                        .optEngine("PyTorch")
                        .optTranslatorFactory(new TextEmbeddingTranslatorFactory())
                        .optProgress(new ProgressBar())
                        .build();

        try (ZooModel<String, float[]> model = criteria.loadModel();
                Predictor<String, float[]> predictor = model.newPredictor()) {
            float[] embedding1 = predictor.predict(text1);
            float[] embedding2 = predictor.predict(text2);

            // Calculate cosine similarity
            double similarity = cosineSimilarity(embedding1, embedding2);

            String output = "Text 1:"+ text1 + "\n" + "Text 2:"+ text2 + "\n" + similarity;


            return output;
            
        }
    }
        // Method to calculate cosine similarity
        private static double cosineSimilarity(float[] vector1, float[] vector2) {
            double dotProduct = 0.0;
            double norm1 = 0.0;
            double norm2 = 0.0;

            for (int i = 0; i < vector1.length; i++) {
                dotProduct += vector1[i] * vector2[i];
                norm1 += Math.pow(vector1[i], 2);
                norm2 += Math.pow(vector2[i], 2);
            }

            return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
        }
}


