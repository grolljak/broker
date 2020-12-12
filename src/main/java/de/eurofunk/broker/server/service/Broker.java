package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.MyMessage;
import org.springframework.stereotype.Component;

@Component
public class Broker {

    private Exchange exchange;
    private QueueService queueService;

    public Broker(Exchange exchange, QueueService queueService) {
        this.exchange = exchange;
        this.queueService = queueService;
    }

    public void send(MyMessage message) {
        this.exchange.send(message);
    }

    public String receive(String deviceName) {
        return queueService.getQueue(deviceName).get();
    }

}
