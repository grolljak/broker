package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.persistance.entity.MessageDeviceEntity;
import de.eurofunk.broker.server.persistance.repository.MessageDeviceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class MessageDeviceService {

    private MessageDeviceRepository repository;
    private QueueService queueService;

    public MessageDeviceService(MessageDeviceRepository repository, QueueService queueService) {
        this.repository = repository;
        this.queueService = queueService;
    }

    @Transactional
    public void registerMessageDevice(MessageDevice device) {
        MessageDeviceEntity entity = toEntity(device);
        repository.save(entity);
        queueService.addQueue(device.getName());
    }

    @Transactional
    public void removeMessageDevice(String name) {
        repository.deleteById(name);
        queueService.removeQueue(name);
    }

    @Transactional
    public List<MessageDevice> getAllMessageDevices() {
        return repository.findAll().stream().map(this::toMessageDevice).collect(toList());
    }

    private MessageDeviceEntity toEntity(MessageDevice device) {
        MessageDeviceEntity entity = new MessageDeviceEntity();
        entity.setName(device.getName());
        return entity;
    }

    private MessageDevice toMessageDevice(MessageDeviceEntity entity) {
        return new MessageDevice(entity.getName());
    }
}
