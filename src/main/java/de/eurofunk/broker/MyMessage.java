package de.eurofunk.broker;

import java.util.Objects;

public class MyMessage {
    private String routingKey;

    public MyMessage(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMessage myMessage = (MyMessage) o;
        return routingKey.equals(myMessage.routingKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routingKey);
    }
}
