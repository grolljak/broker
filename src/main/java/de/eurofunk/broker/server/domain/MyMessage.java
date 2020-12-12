package de.eurofunk.broker.server.domain;

import java.util.Objects;

public class MyMessage {
    private final String routingKey;
    private final String message;

    public MyMessage(String routingKey, String message) {
        this.routingKey = routingKey;
        this.message = message;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMessage myMessage = (MyMessage) o;
        return Objects.equals(routingKey, myMessage.routingKey) &&
                Objects.equals(message, myMessage.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routingKey, message);
    }
}
