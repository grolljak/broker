package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.Exchange;
import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.service.DeviceGroupService;
import de.eurofunk.broker.server.service.QueueService;

public class MulticastExchange implements Exchange {

    DeviceGroupService deviceGroupService;
    QueueService queueService;

    public MulticastExchange(DeviceGroupService deviceGroupService, QueueService queueService) {
        this.deviceGroupService = deviceGroupService;
        this.queueService = queueService;
    }

    @Override
    public void send(Message message) {
        deviceGroupService.getGroup(message.getRoutingKey()).getMessageDevices().forEach(messageDevice -> {
            queueService.getQueue(messageDevice.getName()).add(message.getMessage());
        });
    }
}
