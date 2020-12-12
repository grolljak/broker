package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Broker {

    private Exchange exchange;
    private List<MessageQueue> queues;

    public Broker(Exchange exchange, List<MessageQueue> queues) {
        this.exchange = exchange;
        this.queues = queues;
    }

    void send(MyMessage message) {
        this.exchange.send(message);
    }

    void receive(String deviceName) {
        //find queue by name and return its message
    }
}
