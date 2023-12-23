package si.feri.okusisvet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
public class OkusiSvetApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkusiSvetApplication.class, args);
    }

}
