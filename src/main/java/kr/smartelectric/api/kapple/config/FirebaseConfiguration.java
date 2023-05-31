package kr.smartelectric.api.kapple.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfiguration {
//    @Value("${firebase.sdk.path}")
//    private String firebaseSdkPath;
//    private FirebaseApp firebaseApp;
//
//    @PostConstruct
//    public FirebaseApp initializeFirebaseApp(){
//        Resource resource = new ClassPathResource(firebaseSdkPath);
//
//        try{
//            FileInputStream fis = new FileInputStream(resource.getFile());
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(fis))
//                    .build();
//            firebaseApp = FirebaseApp.initializeApp(options);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return firebaseApp;
//
//    }

}
