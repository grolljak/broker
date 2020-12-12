package de.eurofunk.broker.server;

import de.eurofunk.broker.server.domain.MyMessage;

public interface MessageConsumer {
    MyMessage receive();
}
