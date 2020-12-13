package de.eurofunk.broker.server.component;

import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.service.QueueService;
import org.springframework.stereotype.Component;

@Component
public class Broker {

    private Exchange exchange;
    private QueueService queueService;

    public Broker(Exchange exchange, QueueService queueService) {
        this.exchange = exchange;
        this.queueService = queueService;
    }

    public void send(Message message) {
        this.exchange.send(message);
    }

    public String receive(String deviceName) {
        return queueService.getQueue(deviceName).get();
    }

}
