package si.feri.okusisvet;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.FileInputStream;
import java.io.IOException;


@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties
@EntityScan("si.feri.okusisvet.model")
public class OkusiSvetApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkusiSvetApplication.class, args);
    }

    //TODO seperate firebase app from firebase auth??
    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("./src/main/java/si/feri/okusisvet/security/firebase.json");

        FirebaseOptions options =FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options, "okusi-svet");

        return FirebaseAuth.getInstance(firebaseApp);
    }
}
