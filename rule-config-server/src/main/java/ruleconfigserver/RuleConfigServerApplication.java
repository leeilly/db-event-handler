package ruleconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class RuleConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuleConfigServerApplication.class, args);
	}
}
