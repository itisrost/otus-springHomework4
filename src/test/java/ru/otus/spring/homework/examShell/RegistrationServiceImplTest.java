package ru.otus.spring.homework.examShell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.spring.homework.examShell.model.Student;
import ru.otus.spring.homework.examShell.service.InputOutputService;
import ru.otus.spring.homework.examShell.service.RegistrationService;
import ru.otus.spring.homework.examShell.service.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Тестируем RegistrationServiceImpl")
public class RegistrationServiceImplTest {

    public static final String FREDDIE = "Freddie";

    @Configuration
    static class TestConfig{
        @Bean
        public RegistrationService registrationService(InputOutputService inputOutputService){
            return new RegistrationServiceImpl(inputOutputService);
        }
    }

    @MockBean
    private InputOutputService inputOutputService;

    @Autowired
    private RegistrationService registrationService;

    @Test
    @DisplayName("Пользователь создается корректно")
    void registerStudentTest(){
        Mockito.when(inputOutputService.readLine()).thenReturn(FREDDIE);

        Student student = registrationService.registerStudent();
        assertNotNull(student, "student is null");
        assertEquals(student.getName(), FREDDIE);
    }
}