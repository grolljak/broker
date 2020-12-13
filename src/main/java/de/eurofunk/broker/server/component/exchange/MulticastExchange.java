package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.Exchange;
import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.service.DeviceGroupService;
import de.eurofunk.broker.server.service.QueueService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exchange agent implementation for multicasting messages to devices of specific device group.
 */
public class MulticastExchange implements Exchange {

    DeviceGroupService deviceGroupService;
    QueueService queueService;

    public MulticastExchange(DeviceGroupService deviceGroupService, QueueService queueService) {
        this.deviceGroupService = deviceGroupService;
        this.queueService = queueService;
    }

    @Override
    public void send(Message message) {
        List<String> queuesToBeMessaged = deviceGroupService.getGroup(message.getRoutingKey())
                .getMessageDevices().stream().map(MessageDevice::getName).collect(Collectors.toList());

        queueService.addMessageToQueues(message, queuesToBeMessaged);
    }
}
