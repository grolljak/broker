package de.eurofunk.broker.server;

import de.eurofunk.broker.server.domain.Message;

/**
 * Producer of message.
 */
public interface MessageProducer {

    /**
     * Produces a message and sends it to message broker.
     *
     * @param message the message to be send
     */
    void send(Message message);
}
