package ru.otus.spring.homework.examShell.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import ru.otus.spring.homework.examShell.config.Properties;
import ru.otus.spring.homework.examShell.dao.QuestionsDao;
import ru.otus.spring.homework.examShell.model.Question;
import ru.otus.spring.homework.examShell.model.Student;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    private final Properties yamlProperties;
    private final QuestionsDao questionsDao;
    private final InputOutputService inputOutputService;

    public ExamServiceImpl(Properties yamlProperties,
                           QuestionsDao questionsDao,
                           InputOutputService inputOutputService) {
        this.yamlProperties = yamlProperties;
        this.questionsDao = questionsDao;
        this.inputOutputService = inputOutputService;
    }

    @Override
    public void exam(Student student) {

        int score = takeExam(questionsDao.getQuestions());

        student.setScore(score);
        student.setExamPassed(score >= yamlProperties.getScoreToPass());

        if (student.isExamPassed()) {
            inputOutputService.printMessage("exam.passed", student.getName(), score);
        } else {
            inputOutputService.printMessage("exam.failed", student.getName(), score);
        }
    }

    private int takeExam(List<Question> questions) {
        int score = 0;
        for (Question question : questions) {
            inputOutputService.printLine(question.getQuestion());
            if (StringUtils.equals(inputOutputService.readLine().toLowerCase(), question.getAnswer().toLowerCase())) {
                score++;
            }
        }
        return score;
    }
}