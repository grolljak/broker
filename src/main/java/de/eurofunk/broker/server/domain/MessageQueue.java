package de.eurofunk.broker.server.domain;

import java.util.LinkedList;

/**
 * Message Queue class, holds messages in queue for each device.
 * Queues are only held in memory and they are initiated at the start of application.
 */
public class MessageQueue {

    private final String name;
    private LinkedList<String> messages = new LinkedList<>();

    public MessageQueue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String get() {
        String retrievedMessage = messages.poll();
        if (retrievedMessage != null) {
            return retrievedMessage;
        }
        throw new IllegalStateException("No messages to be retrieved.");
    }

    public int size() {
        return messages.size();
    }

    public void add(String message) {
        messages.add(message);
    }
}
