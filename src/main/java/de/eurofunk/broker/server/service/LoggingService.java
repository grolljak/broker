package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LoggingService {

    private final Set<String> keywords = new HashSet<>();

    Logger log = LogManager.getLogger(LoggingService.class);

    public String checkMessage(Message message, List<String> recipients) {
        boolean containsSomeKeyword = keywords.stream().anyMatch(keyword -> message.getMessage().contains(keyword));
        if (containsSomeKeyword) {
            return logAlert(message, recipients);
        }
        return "";
    }

    private String logAlert(Message message, List<String> recipients) {
        String logMessage = message.toString() + ", " + "Recipients: " + Arrays.toString(recipients.toArray());
        log.warn(logMessage);
        return logMessage;
    }

    public void addKeyword(String keyword) {
        keywords.add(keyword);
    }

    public void removeKeyword(String keyword) {
        keywords.remove(keyword);
    }
}
