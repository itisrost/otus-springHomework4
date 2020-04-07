package ru.otus.spring.homework.examShell.service;

import java.io.PrintStream;
import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.homework.examShell.config.LocalizationProperties;

@RequiredArgsConstructor
public class InputOutputServiceImpl implements InputOutputService {

    private final PrintStream output;
    private final Scanner input;
    private final LocalizationProperties localizationProperties;

    public String readLine() {
        return input.nextLine();
    }

    public void printLine(String text) {
        output.println(text);
    }

    public void printMessage(String messageName, Object... args) {
        printLine(localizationProperties.getLocalizedMessage(messageName, args));
    }
}