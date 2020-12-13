package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.Exchange;
import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.service.QueueService;

public class BroadcastExchange implements Exchange {
    QueueService queueService;

    public BroadcastExchange(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void send(Message message) {
        queueService.getAllQueues().forEach(queue -> queue.add(message.getMessage()));

    }
}
