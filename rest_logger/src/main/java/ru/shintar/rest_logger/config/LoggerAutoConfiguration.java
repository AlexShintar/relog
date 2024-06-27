package ru.shintar.rest_logger.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.shintar.rest_logger.logger.LoggerFilter;
import ru.shintar.rest_logger.logger.LoggerHandler;


@Configuration
@EnableConfigurationProperties(LoggerProperties.class)
public class LoggerAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "relogger", value = "enabled", havingValue = "true", matchIfMissing = false)
    public LoggerFilter loggerFilter(LoggerHandler loggerHandler) {
        return new LoggerFilter(loggerHandler);
    }

    @Bean
    @ConditionalOnMissingBean
    public LoggerHandler loggerHandler(LoggerProperties properties) {
        return new LoggerHandler(properties);
    }
}
