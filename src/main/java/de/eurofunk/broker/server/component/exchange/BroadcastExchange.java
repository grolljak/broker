package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.Exchange;
import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.service.QueueService;

/**
 * Exchange agent implementation for broadcasting messages to all devices.
 */
public class BroadcastExchange implements Exchange {
    QueueService queueService;

    public BroadcastExchange(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void send(Message message) {
        queueService.addMessageToAllQueues(message);
    }
}
