package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.persistance.repository.MessageDeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageDeviceService {

    private MessageDeviceRepository repository;
    private QueueService queueService;

    public MessageDeviceService(MessageDeviceRepository repository, QueueService queueService) {
        this.repository = repository;
        this.queueService = queueService;
    }

    public void registerMessageDevice(MessageDevice device) {
        //repository.save() //device to entity here
        queueService.addQueue(device.getName()); //move to separate service
    }

    public void removeMessageDevice(MessageDevice device) {
        //repository.remove()
        queueService.removeQueue(device.getName());
    }
}
