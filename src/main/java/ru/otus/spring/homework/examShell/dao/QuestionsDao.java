package ru.otus.spring.homework.examShell.dao;

import java.util.List;

import ru.otus.spring.homework.examShell.model.Question;


public interface QuestionsDao {

    List<Question> getQuestions();

}
