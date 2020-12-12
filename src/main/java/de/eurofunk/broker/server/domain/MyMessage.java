package de.eurofunk.broker.server.domain;

import java.util.Objects;

public class MyMessage {
    private final String routingKey;
    private final String message;
    private final MessageSemantic semantic;

    public MyMessage(String routingKey, MessageSemantic semantic, String message) {
        this.routingKey = routingKey;
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
        MyMessage myMessage = (MyMessage) o;
        return Objects.equals(routingKey, myMessage.routingKey) &&
                Objects.equals(message, myMessage.message) &&
                semantic == myMessage.semantic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routingKey, message, semantic);
    }
}
