package de.eurofunk.broker.server;

/**
 * Consumer of message.
 */
public interface MessageConsumer {

    /**
     * Receives a message from message broker.
     *
     * @return message or throws exception if no message is present
     */
    String receive();
}
