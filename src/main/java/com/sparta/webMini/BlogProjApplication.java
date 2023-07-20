package com.sparta.webMini;

import com.sparta.webMini.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Import(CorsConfig.class)
public class BlogProjApplication {

	public static void main(String[] args) {

		SpringApplication.run(BlogProjApplication.class, args);
	}

}