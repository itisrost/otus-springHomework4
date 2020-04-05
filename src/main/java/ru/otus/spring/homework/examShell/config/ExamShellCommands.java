package ru.otus.spring.homework.examShell.config;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.homework.examShell.model.Student;
import ru.otus.spring.homework.examShell.service.ExamService;
import ru.otus.spring.homework.examShell.service.RegistrationService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class ExamShellCommands {

    private final RegistrationService registrationService;
    private final LocalizationProperties localizationProperties;
    private final ExamService examService;

    private Student student;

    @ShellMethod(value = "Register", key = {"r", "reg"})
    public String register() {
        this.student = registrationService.registerStudent();

        return localizationProperties.getLocalizedMessage("registration.done");
    }

    @ShellMethod(value = "Begin exam", key = {"e", "exam"})
    @ShellMethodAvailability(value = "isExamAvailable")
    public String exam() {
        examService.exam(student);
        return "";
    }

    private Availability isExamAvailable() {
        return student == null ? Availability.unavailable(localizationProperties.getLocalizedMessage("registration.none")) : Availability.available();
    }
}