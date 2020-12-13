package de.eurofunk.broker.server.component;

import de.eurofunk.broker.server.service.MessageDeviceService;
import de.eurofunk.broker.server.service.QueueService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class QueueStartupHandler {

    QueueService queueService;
    MessageDeviceService messageDeviceService;

    public QueueStartupHandler(QueueService queueService, MessageDeviceService messageDeviceService) {
        this.queueService = queueService;
        this.messageDeviceService = messageDeviceService;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        messageDeviceService.getAllMessageDevices().forEach(device -> queueService.addQueue(device.getName()));
    }
}