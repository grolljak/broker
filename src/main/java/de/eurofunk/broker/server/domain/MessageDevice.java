package de.eurofunk.broker.server.domain;

import de.eurofunk.broker.server.MessageConsumer;
import de.eurofunk.broker.server.MessageProducer;

public class MessageDevice implements MessageConsumer, MessageProducer {

    private final String name;

    public MessageDevice(String name) {
        this.name = name;
    }

    @Override
    public String receive() {
        //broker receive()
        return "";
    }

    @Override
    public void send(MyMessage message) {
        // broker.send()
    }

    public String getName() {
        return name;
    }
}
