package de.eurofunk.broker.service;

import de.eurofunk.broker.MessageQueue;
import de.eurofunk.broker.MyMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Exchange {
    private List<MessageQueue> queues;

    public Exchange(List<MessageQueue> queues) {
        this.queues = queues;
    }

    void send(MyMessage message) {
        //look into message key and route to correct queue
    }

}
