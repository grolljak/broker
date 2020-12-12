package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.persistance.repository.DeviceGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceGroupService {

    private DeviceGroupRepository repository;

    public DeviceGroupService(DeviceGroupRepository repository) {
        this.repository = repository;
    }

    public void registerDeviceGroup(DeviceGroup group) {
        //repository.save()
    }

    public void removeDeviceGroup(DeviceGroup group) {
        //repository.delete()
    }

    public DeviceGroup getGroup(String name) {
        //return repository.findById(routingKey).get();
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
