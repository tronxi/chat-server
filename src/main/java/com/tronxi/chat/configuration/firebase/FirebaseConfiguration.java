package com.tronxi.chat.configuration.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseConfiguration {

    @Value("${firebaseConfig}")
    private String firebaseConfig;

   @PostConstruct
    public void initialize() {
        try {
            InputStream stream = new ByteArrayInputStream(firebaseConfig.getBytes());
            FirebaseOptions options = new FirebaseOptions
                    .Builder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
}
