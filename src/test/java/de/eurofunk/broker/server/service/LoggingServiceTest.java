package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.eurofunk.broker.server.domain.MessageSemantic.DIRECT;
import static org.junit.jupiter.api.Assertions.*;

class LoggingServiceTest {

    LoggingService loggingService;

    @BeforeEach
    void setUp() {
        loggingService = new LoggingService();
    }

    @Test
    @DisplayName("when sending message with keyword, it should log a message")
    public void simpleKeywordTest() {
        //given
        loggingService.addKeyword("keyword");
        Message message = new Message("a", DIRECT, "keyword");

        //when
        String logMessage = loggingService.checkMessage(message, List.of());

        //then
        assertNotEquals("", logMessage);
    }

    @Test
    @DisplayName("when sending message with keyword present in the message, it should log a message")
    public void parsingTest() {
        //given
        loggingService.addKeyword("keyword");
        Message message = new Message("a", DIRECT, "there is keyword somewhere here");

        //when
        String logMessage = loggingService.checkMessage(message, List.of());

        //then
        assertNotEquals("", logMessage);
    }

    @Test
    @DisplayName("when sending message with keyword present among registered keywords, it should log a message")
    public void multipleKeywordsParsingTest() {
        //given
        loggingService.addKeyword("keyword");
        loggingService.addKeyword("anotherKeyword");
        loggingService.addKeyword("beep");
        Message message = new Message("a", DIRECT, "there is beep somewhere here");

        //when
        String logMessage = loggingService.checkMessage(message, List.of());

        //then
        assertNotEquals("", logMessage);
    }

    @Test
    @DisplayName("when sending message with no keyword, it should not log a message")
    public void noneMatchingTest() {
        //given
        loggingService.addKeyword("keyword");
        loggingService.addKeyword("anotherKeyword");
        loggingService.addKeyword("beep");
        Message message = new Message("a", DIRECT, "there is nothing triggering the logging");

        //when
        String logMessage = loggingService.checkMessage(message, List.of());

        //then
        assertEquals("", logMessage);
    }
}