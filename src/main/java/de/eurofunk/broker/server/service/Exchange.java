package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Exchange {
    private Map<String, MessageQueue> queues;
    private Map<String, DeviceGroup> deviceGroups;

    public Exchange(Map<String, MessageQueue> queues, Map<String, DeviceGroup> deviceGroups) {
        this.queues = queues;
        this.deviceGroups = deviceGroups;
    }

    void send(MyMessage message) {
        switch (message.getSemantic()) {
            case DIRECT:
                queues.get(message.getRoutingKey()).add(message.getMessage());
                break;
            case MULTICAST:
                deviceGroups.get(message.getRoutingKey()).getMessageDevices().forEach(messageDevice -> {
                    queues.get(messageDevice.getName()).add(message.getMessage());
                });
                break;
            case BROADCAST:
                queues.forEach((name, queue) -> {
                    queues.get(name).add(message.getMessage());
                });
                break;
            default:
                throw new UnsupportedOperationException("Unsupported semantic " + message.getSemantic());
        }
    }
}
