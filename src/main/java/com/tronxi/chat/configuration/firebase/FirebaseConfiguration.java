package com.tronxi.chat.configuration.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
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
            JSONParser jsonParser = new JSONParser();
            String firebaseConfigWithSpaces = firebaseConfig.replace("#", " ");
            JSONObject jsonObject = (JSONObject) jsonParser.parse(firebaseConfigWithSpaces);
            System.out.println(jsonObject.toJSONString());
            InputStream stream = new ByteArrayInputStream(jsonObject.toJSONString().getBytes());
            FirebaseOptions options = new FirebaseOptions
                    .Builder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
   }
}
