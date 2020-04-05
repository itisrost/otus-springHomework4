package ru.otus.spring.homework.examShell.config;

import java.util.Scanner;

import ru.otus.spring.homework.examShell.service.InputOutputServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public InputOutputServiceImpl consoleIO(LocalizationProperties localizationProperties) {
        return new InputOutputServiceImpl(System.out, new Scanner(System.in), localizationProperties);
    }
}