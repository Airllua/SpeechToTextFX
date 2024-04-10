package com.example.texttospeechfx;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpeechToText {
    public static void main(String[] args) throws Exception {
        String credentialsPath = "src\\main\\resources\\iconic-range-419414-54b862118e60.json";

        //иниц. клиента
        try (SpeechClient speechClient = SpeechClient.create(SpeechSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(
                        GoogleCredentials.fromStream(new FileInputStream(credentialsPath))))
                .build())) {

            Path path = Paths.get("src\\main\\resources\\record_out.wav");
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("ru-RU")
                            .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();
            RecognizeResponse response = speechClient.recognize(config, audio);

            for (SpeechRecognitionResult result : response.getResultsList()) {
                for (SpeechRecognitionAlternative alternative : result.getAlternativesList()) {
                    System.out.printf("Transcription: %s%n", alternative.getTranscript());
                }
            }
        }
    }
}
