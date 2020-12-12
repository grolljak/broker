package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Exchange {
    private List<MessageQueue> queues;
    private List<DeviceGroup> deviceGroups;

    public Exchange(List<MessageQueue> queues, List<DeviceGroup> deviceGroups) {
        this.queues = queues;
        this.deviceGroups = deviceGroups;
    }

    void send(MyMessage message) {
        //look into message key and route to correct queue
    }

}
