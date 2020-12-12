package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.mapper.DeviceGroupMapper;
import de.eurofunk.broker.server.persistance.entity.DeviceGroupEntity;
import de.eurofunk.broker.server.persistance.repository.DeviceGroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DeviceGroupService {

    private DeviceGroupRepository repository;
    private DeviceGroupMapper mapper;

    public DeviceGroupService(DeviceGroupRepository repository, DeviceGroupMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void registerDeviceGroup(DeviceGroup group) {
        repository.save(mapper.mapDeviceGroupToEntity(group));
    }

    public void removeDeviceGroup(DeviceGroup group) {
        repository.deleteById(group.getName());
    }

    @Transactional
    public DeviceGroup getGroup(String name) {
        Optional<DeviceGroupEntity> optional = repository.findById(name);
        if (optional.isPresent()) {
            return mapper.mapEntityToDeviceGroup(optional.get());
        }
        throw new IllegalStateException("Group " + name + " is not registered.");
    }

}
