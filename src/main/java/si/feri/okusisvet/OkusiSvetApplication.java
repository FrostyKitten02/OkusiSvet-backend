package si.feri.okusisvet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
@EntityScan("si.feri.okusisvet.model")
public class OkusiSvetApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkusiSvetApplication.class, args);
    }

}
