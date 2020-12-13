package de.eurofunk.broker.server;

import de.eurofunk.broker.server.domain.Message;

/**
 * Exchange interface.
 * Exchanges are message routing agents, responsible for routing the messages different queues.
 */
public interface Exchange {

    /**
     * Routes the message to different queues, based on message semantic provided by the caller.
     * @param message the message to be sent
     */
    void send(Message message);
}
