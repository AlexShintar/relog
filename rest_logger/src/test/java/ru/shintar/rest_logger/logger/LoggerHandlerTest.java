package ru.shintar.rest_logger.logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import ru.shintar.rest_logger.config.LoggerProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(OutputCaptureExtension.class)
@ExtendWith(MockitoExtension.class)
public class LoggerHandlerTest {

    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockFilterChain chain;
    MockedStatic<LoggerFactory> mockedLoggerFactory;
    @Mock
    Logger logger;
    LoggerHandler loggerHandler;

    @BeforeEach
    void setUp() {
        this.request = new MockHttpServletRequest("GET", "/test");
        this.request.addHeader("Test", "test");
        this.response = new MockHttpServletResponse();
        this.response.setStatus(200);
        this.response.setHeader("Test", "test");
        this.chain = new MockFilterChain();
        this.mockedLoggerFactory = mockStatic(LoggerFactory.class);
        this.mockedLoggerFactory.when(() -> LoggerFactory.getLogger(LoggerHandler.class)).thenReturn(logger);
        this.loggerHandler = new LoggerHandler(new LoggerProperties());
    }

    @AfterEach
    void tearDown() {
        this.mockedLoggerFactory.close();
    }

    @Test
    void loggerHandlerLoggingRequest() {
        ContentCachingRequestWrapper req = spy(new ContentCachingRequestWrapper(this.request));
        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(this.response);
        ArgumentCaptor<String> captorMethod = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captorURI = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captorHeader = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> captorStatus = ArgumentCaptor.forClass(Integer.class);

        doNothing().when(this.logger).info(anyString(), captorMethod.capture(),
                captorURI.capture(), captorStatus.capture(), anyLong());

        doNothing().when(this.logger).info(anyString(), captorHeader.capture());

        this.loggerHandler.logging(req, resp, 0);

        assertThat(captorMethod.getValue()).contains(request.getMethod());
        assertThat(request.getRequestURL()).contains(captorURI.getValue());
        assertThat(captorHeader.getValue()).contains(request.getHeader("Test"));
        assertThat(captorStatus.getValue()).isEqualTo(response.getStatus());
    }
}
