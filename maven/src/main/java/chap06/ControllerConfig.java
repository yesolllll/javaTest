package chap06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {
	@Bean
	public HelloController helloController() {
		return new HelloController();
	}
}
