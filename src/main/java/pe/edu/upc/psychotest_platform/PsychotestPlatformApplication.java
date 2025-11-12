package pe.edu.upc.psychotest_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PsychotestPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsychotestPlatformApplication.class, args);
	}


}
