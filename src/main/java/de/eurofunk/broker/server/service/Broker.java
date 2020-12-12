package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Broker {

    private Exchange exchange;
    private Map<String, MessageQueue> queues;

    public Broker(Exchange exchange, Map<String, MessageQueue> queues) {
        this.exchange = exchange;
        this.queues = queues;
    }

    public void send(MyMessage message) {
        this.exchange.send(message);
    }

    public String receive(String deviceName) {
        MessageQueue messageQueue = queues.get(deviceName);
        if (messageQueue != null) {
            return messageQueue.get();
        }
        throw new IllegalArgumentException("Device with name" + deviceName + " does not exist.");
    }

    public void addQueue(String name) {
        queues.put(name, new MessageQueue(name));
    }

    public void removeQueue(String name) {
        queues.remove(name);
    }
}
