package de.eurofunk.broker.server.domain;

public enum MessageSemantic {
    DIRECT("direct"), MULTICAST("multicast"), BROADCAST("broadcast");

    private final String value;

    MessageSemantic(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
