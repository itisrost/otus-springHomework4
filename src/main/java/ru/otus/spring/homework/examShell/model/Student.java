package ru.otus.spring.homework.examShell.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Student {

    private final String name;
    private int score;
    private boolean examPassed;
}
