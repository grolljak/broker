package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.domain.MessageQueue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueueService {
    private LoggingService loggingService;
    private Map<String, MessageQueue> queues = new HashMap<>();

    public QueueService(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    public void addQueue(String name) {
        queues.put(name, new MessageQueue(name));
    }

    public void removeQueue(String name) {
        queues.remove(name);
    }

    public MessageQueue getQueue(String deviceName) {
        MessageQueue messageQueue = queues.get(deviceName);
        if (messageQueue != null) {
            return messageQueue;
        }
        throw new RuntimeException("Queue for device with name " + deviceName + " was not correctly initiated.");
    }

    public List<MessageQueue> getAllQueues() {
        return new ArrayList<>(queues.values());
    }

    public void addMessageToQueues(Message message, List<String> queuesToBeMessaged) {
        loggingService.checkMessage(message, queuesToBeMessaged);
        queuesToBeMessaged.forEach(name -> getQueue(name).add(message.getMessage()));
    }

    public void addMessageToAllQueues(Message message) {
        addMessageToQueues(message, new ArrayList<>(queues.keySet()));
    }
}
