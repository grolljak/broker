package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Broker {

    private Exchange exchange;
    private Map<String, MessageQueue> queues;

    public Broker(Exchange exchange, Map<String, MessageQueue> queues) {
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
