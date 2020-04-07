package ru.otus.spring.homework.examShell;

import ru.otus.spring.homework.examShell.config.Properties;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class ExamShellApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamShellApplication.class, args);
	}
}