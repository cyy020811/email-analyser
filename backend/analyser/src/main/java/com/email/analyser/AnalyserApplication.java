package com.email.analyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.email.analyser.service.UserService;

@SpringBootApplication
public class AnalyserApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AnalyserApplication.class, args);

		var service = context.getBean(UserService.class);
		service.showUser("19cd0e0a-2198-4dda-bf6c-928ab8566a1d");
	}

}
