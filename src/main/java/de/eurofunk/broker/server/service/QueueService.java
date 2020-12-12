package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.MessageQueue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueueService {

    private Map<String, MessageQueue> queues = new HashMap<>();

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
        throw new IllegalArgumentException("Device with name" + deviceName + " was not registered.");
    }

    public List<MessageQueue> getAllQueues() {
        return new ArrayList<>(queues.values());
    }
}
