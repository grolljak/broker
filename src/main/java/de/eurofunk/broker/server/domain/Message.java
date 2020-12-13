package de.eurofunk.broker.server.domain;

import java.util.Objects;

public class Message {

    private final String routingKey;
    private final String message;
    private final MessageSemantic semantic;

    public Message(String routingKey, MessageSemantic semantic, String message) {
        this.routingKey = routingKey;
        this.message = message;
        this.semantic = semantic;
    }

    public Message(MessageSemantic semantic, String message) {
        this.routingKey = "";
        this.message = message;
        this.semantic = semantic;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public String getMessage() {
        return message;
    }

    public MessageSemantic getSemantic() {
        return semantic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(routingKey, message.routingKey) &&
                Objects.equals(this.message, message.message) &&
                semantic == message.semantic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routingKey, message, semantic);
    }
}
