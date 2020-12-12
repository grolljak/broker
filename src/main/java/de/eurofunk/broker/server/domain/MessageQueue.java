package de.eurofunk.broker.server.domain;

import java.util.LinkedList;

public class MessageQueue {

    private final String name;
    private LinkedList<String> messages = new LinkedList<>();

    public MessageQueue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFirst() {
        return messages.getFirst();
    }

    public int size() {
        return messages.size();
    }
}
