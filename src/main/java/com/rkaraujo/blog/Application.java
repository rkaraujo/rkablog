package com.rkaraujo.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.rkaraujo.blog.config.WebConfig;
import com.rkaraujo.blog.config.WebSecurityConfig;

@SpringBootApplication
@Import({ WebSecurityConfig.class, WebConfig.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
