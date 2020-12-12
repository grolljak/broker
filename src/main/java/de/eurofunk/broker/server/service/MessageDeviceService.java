package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.persistance.entity.MessageDeviceEntity;
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
        MessageDeviceEntity entity = toEntity(device);
        repository.save(entity);
        queueService.addQueue(device.getName());
    }

    public void removeMessageDevice(MessageDevice device) {
        repository.deleteById(device.getName());
        queueService.removeQueue(device.getName());
    }

    private MessageDeviceEntity toEntity(MessageDevice device) {
        MessageDeviceEntity entity = new MessageDeviceEntity();
        entity.setName(device.getName());
        return entity;
    }
}
