package de.eurofunk.broker.server.component;

import de.eurofunk.broker.server.domain.MyMessage;
import de.eurofunk.broker.server.service.DeviceGroupService;
import de.eurofunk.broker.server.service.QueueService;
import org.springframework.stereotype.Component;

@Component
public class Exchange {

    private QueueService queueService;
    private DeviceGroupService deviceGroupService;

    public Exchange(QueueService queueService, DeviceGroupService deviceGroupService) {
        this.queueService = queueService;
        this.deviceGroupService = deviceGroupService;
    }

    public void send(MyMessage message) {
        switch (message.getSemantic()) {
            case DIRECT:
                queueService.getQueue(message.getRoutingKey()).add(message.getMessage()); //todo: validation of messages!
                break;
            case MULTICAST:
                deviceGroupService.getGroup(message.getRoutingKey()).getMessageDevices().forEach(messageDevice -> {
                    queueService.getQueue(messageDevice.getName()).add(message.getMessage());
                });
                break;
            case BROADCAST:
                queueService.getAllQueues().forEach(queue -> queue.add(message.getMessage()));
                break;
            default:
                throw new UnsupportedOperationException("Unsupported semantic " + message.getSemantic());
        }
    }
}
