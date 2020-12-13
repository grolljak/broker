package de.eurofunk.broker.server.component;

import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.service.ExchangeService;
import de.eurofunk.broker.server.service.QueueService;
import org.springframework.stereotype.Component;

@Component
public class Broker {

    private ExchangeService exchangeService;
    private QueueService queueService;

    public Broker(ExchangeService exchangeService, QueueService queueService) {
        this.exchangeService = exchangeService;
        this.queueService = queueService;
    }

    public void send(Message message) {
        this.exchangeService.send(message);
    }

    public String receive(String deviceName) {
        return queueService.getQueue(deviceName).get();
    }

}
