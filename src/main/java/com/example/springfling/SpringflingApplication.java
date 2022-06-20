package com.example.springfling;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringflingApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringflingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			var letters = "ooxx".toLowerCase().split("");
			var ex = Arrays.stream(letters).filter((l) -> l == "x").collect(Collectors.counting());
			var oh = Arrays.stream(letters).filter((l) -> l == "o").toArray().length;
			var a = ex == oh;

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}
}
