package ru.shintar.rest_logger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "relogger")
public class LoggerProperties {
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private Boolean logException = true;
    private Boolean logInfo = true;
    private Boolean logRequestHeaders = true;
    private Boolean logResponseHeaders = true;

    public Boolean istLogException() {
        return logException;
    }

    public void setLogException(Boolean logException) {
        this.logException = logException;
    }

    public Boolean isLogInfo() {
        return logInfo;
    }

    public void setLogInfo(Boolean logInfo) {
        this.logInfo = logInfo;
    }

    public Boolean isLogRequestHeaders() {
        return logRequestHeaders;
    }

    public void setLogRequestHeaders(Boolean logRequestHeaders) {
        this.logRequestHeaders = logRequestHeaders;
    }

    public Boolean isLogResponseHeaders() {
        return logResponseHeaders;
    }

    public void setLogResponseHeaders(Boolean logResponseHeaders) {
        this.logResponseHeaders = logResponseHeaders;
    }
}