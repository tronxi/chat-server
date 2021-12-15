package com.tronxi.chat.configuration.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseConfiguration {

    @Value("${firebaseConfig:empty}")
    private String firebaseConfig;

   @PostConstruct
    public void initialize() {
       if(isEmpty(firebaseConfig))
           return;
        try {
            JSONParser jsonParser = new JSONParser();
            String firebaseConfigWithSpaces = firebaseConfig.replace("#", " ");
            JSONObject jsonObject = (JSONObject) jsonParser.parse(firebaseConfigWithSpaces);

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

   public boolean isEmpty(String firebaseConfig) {
        return firebaseConfig.equals("empty") || StringUtils.isEmpty(firebaseConfig) || StringUtils.isBlank(firebaseConfig);
   }
}
