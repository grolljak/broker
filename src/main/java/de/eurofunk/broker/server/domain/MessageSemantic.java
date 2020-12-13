package de.eurofunk.broker.server.domain;

/**
 * Message Semantic, denotes the type of Exchange agent which processes message.
 */
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
