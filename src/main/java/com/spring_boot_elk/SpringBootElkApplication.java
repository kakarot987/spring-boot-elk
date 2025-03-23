package com.spring_boot_elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootElkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootElkApplication.class, args);
	}

}
@RestController
class LogController {
	private static final Logger logger = LoggerFactory.getLogger(LogController.class);

	@GetMapping("/log")
	public String generateLog(@RequestParam(defaultValue = "info") String level) {
		switch (level.toLowerCase()) {
			case "error":
				logger.error("This is an ERROR log");
				break;
			case "warn":
				logger.warn("This is a WARN log");
				break;
			default:
				logger.info("This is an INFO log");
				break;
		}
		return "Log generated with level: " + level;
	}
}