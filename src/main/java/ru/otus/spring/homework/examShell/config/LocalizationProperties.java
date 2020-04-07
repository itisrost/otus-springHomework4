package ru.otus.spring.homework.examShell.config;

import java.util.Locale;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class LocalizationProperties {

    private final Properties properties;
    private final MessageSource messageSource;

    @Getter
    private Locale locale;

    public LocalizationProperties(Properties properties, MessageSource messageSource) {
        this.properties = properties;
        this.messageSource = messageSource;

        if (StringUtils.isBlank(properties.getLocale())) {
            locale = Locale.getDefault();
        } else {
            locale = Locale.forLanguageTag(properties.getLocale());
        }
    }

    public String getLocalizedFileName() {
        StringBuilder localizedName = new StringBuilder(properties.getQuestionsFile().getNamePrefix());
        localizedName.append("_")
                .append(locale.toLanguageTag())
                .append(".")
                .append(properties.getQuestionsFile().getExtension());

        return localizedName.toString();
    }

    public String getLocalizedMessage(String messageName, Object... args) {
        return messageSource.getMessage(messageName, args, locale);
    }
}