package ru.otus.spring.homework.examShell;

import java.util.List;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.homework.examShell.config.Properties;
import ru.otus.spring.homework.examShell.dao.QuestionsDao;
import ru.otus.spring.homework.examShell.dao.QuestionsDaoImpl;
import ru.otus.spring.homework.examShell.model.Question;
import ru.otus.spring.homework.examShell.config.LocalizationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Тестируем QuestionsDaoImpl")
class QuestionsDaoImplTest {

	@ComponentScan("ru.otus.spring.homework.examShell.dao")
	@Configuration
	static class Config{
	}

	@MockBean
	private LocalizationProperties localizationProperties;

	@MockBean
	private Properties properties;

	@Autowired
	private QuestionsDao questionsDao;

	@Test
	@DisplayName("Тестируем метод getQuestions")
	void getQuestionsTest() {

		Mockito.when(localizationProperties.getLocalizedFileName())
				.thenReturn("testQuestions.csv");

		List<Question> questions = questionsDao.getQuestions();

		assertNotNull(questions, "question list is null");
		assertNotEquals(0, questions.size(), "question list is empty");
		assertEquals(2, questions.size());
		assertEquals("Test question 1?", questions.get(0).getQuestion());
		assertEquals("answer2", questions.get(1).getAnswer());
	}

}