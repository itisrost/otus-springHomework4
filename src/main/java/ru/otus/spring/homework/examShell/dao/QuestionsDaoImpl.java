package ru.otus.spring.homework.examShell.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.homework.examShell.config.Properties;
import ru.otus.spring.homework.examShell.model.Question;
import ru.otus.spring.homework.examShell.config.LocalizationProperties;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionsDaoImpl implements QuestionsDao {

    private final LocalizationProperties localizationProperties;
    private final Properties properties;

    @Override
    public List<Question> getQuestions() {
        List<Question> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(getFileFromResources(localizationProperties.getLocalizedFileName()))) {
            while (scanner.hasNextLine()) {
                result.add(getQuestionFromLine(scanner.nextLine(), ";"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            resource = classLoader.getResource(properties.getQuestionsFile().getDefaultName());
        }

        return new File(resource.getFile());
    }

    private static Question getQuestionFromLine(String line, String delimeter) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(delimeter);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return new Question(values.get(0), values.get(1));
    }
}