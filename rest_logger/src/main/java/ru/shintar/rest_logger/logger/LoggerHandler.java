package ru.shintar.rest_logger.logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ru.shintar.rest_logger.config.LoggerProperties;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

public class LoggerHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoggerHandler.class);
    private final LoggerProperties properties;

    public LoggerHandler(LoggerProperties properties) {
        this.properties = properties;
    }

    public void logging(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, long startTime) {

        if (properties.isLogRequestHeaders()) {
            logger.info("Request Headers: {}", getHeaders(request));
        }
        if (properties.isLogResponseHeaders()) {
            logger.info("Response Headers: {}", getHeaders(response));
        }
        if (properties.isLogInfo()) {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Method: {}; URL: {}; Status: {}; Duration: {}",
                    request.getMethod(), request.getRequestURI(), response.getStatus(), duration);
        }
    }

    public void handleException(Throwable cause, ContentCachingResponseWrapper response) throws IOException {
        if (properties.istLogException()) {
            String message = cause.getCause().getMessage();
            response.resetBuffer();
            response.getWriter().write("Error: " + " [" + message + "]");
            response.getWriter().flush();
            logger.error("[ERROR] Status: {},  Message: {}", response.getStatus(), message);
        }
    }

    private String getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .map(headerName -> headerName + ": " + Collections.list(request.getHeaders(headerName)))
                .collect(Collectors.joining(". "));
    }

    private String getHeaders(HttpServletResponse response) {
        return response.getHeaderNames().stream()
                .map(headerName -> headerName + ": " + response.getHeaders(headerName))
                .collect(Collectors.joining(". "));
    }
}
