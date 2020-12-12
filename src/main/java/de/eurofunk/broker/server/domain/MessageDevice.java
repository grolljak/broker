package de.eurofunk.broker.server.domain;

import de.eurofunk.broker.server.MessageConsumer;
import de.eurofunk.broker.server.MessageProducer;
import de.eurofunk.broker.server.component.Broker;
import de.eurofunk.broker.SpringContext;

public class MessageDevice implements MessageConsumer, MessageProducer {

    private final String name;

    public MessageDevice(String name) {
        this.name = name;
    }

    @Override
    public String receive() {
        Broker broker = SpringContext.getBean(Broker.class);
        return broker.receive(name);
    }

    @Override
    public void send(MyMessage message) {
        Broker broker = SpringContext.getBean(Broker.class);
        broker.send(message);
    }

    public String getName() {
        return name;
    }

}
