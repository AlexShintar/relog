package ru.shintar.rest_logger.logger;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

public class LoggerFilter extends OncePerRequestFilter {

    private final LoggerHandler loggerHandler;

    @Autowired
    public LoggerFilter(LoggerHandler loggerHandler) {
        this.loggerHandler = loggerHandler;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } catch (Throwable e) {
            loggerHandler.handleException(e, responseWrapper);
        } finally {
            loggerHandler.logging(requestWrapper, responseWrapper, startTime);
            responseWrapper.copyBodyToResponse();
        }
    }
}
