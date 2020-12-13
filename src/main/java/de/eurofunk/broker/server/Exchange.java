package de.eurofunk.broker.server;

import de.eurofunk.broker.server.domain.Message;

public interface Exchange {
    void send(Message message);
}
