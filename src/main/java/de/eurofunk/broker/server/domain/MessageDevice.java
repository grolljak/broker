package de.eurofunk.broker.server.domain;

import de.eurofunk.broker.server.MessageConsumer;
import de.eurofunk.broker.server.MessageProducer;

public class MessageDevice implements MessageConsumer, MessageProducer {

    @Override
    public MyMessage receive() {
        //broker receive()
        return null;
    }

    @Override
    public void send() {
       // broker.send()
    }
}
