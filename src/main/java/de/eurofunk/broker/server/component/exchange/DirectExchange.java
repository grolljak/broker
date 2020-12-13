package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.Exchange;
import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.service.QueueService;

import java.util.List;

public class DirectExchange implements Exchange {

    QueueService queueService;

    public DirectExchange(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void send(Message message) {
        queueService.addMessageToQueues(message, List.of(message.getRoutingKey()));
    }
}
