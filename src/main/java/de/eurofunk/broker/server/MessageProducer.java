package de.eurofunk.broker.server;

import de.eurofunk.broker.server.domain.Message;

public interface MessageProducer {
    void send(Message message);
}
