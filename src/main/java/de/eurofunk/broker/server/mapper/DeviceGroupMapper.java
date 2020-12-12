package de.eurofunk.broker.server.mapper;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.persistance.entity.DeviceGroupEntity;
import de.eurofunk.broker.server.persistance.entity.MessageDeviceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class DeviceGroupMapper {

    public DeviceGroupEntity mapDeviceGroupToEntity(DeviceGroup group) {
        DeviceGroupEntity entity = new DeviceGroupEntity();
        entity.setName(group.getName());
        entity.setMessageDevices(toMessageDeviceEntities(group.getMessageDevices()));
        return entity;
    }

    private List<MessageDeviceEntity> toMessageDeviceEntities(List<MessageDevice> messageDevices) {
        return messageDevices.stream().map(this::mapMessageDeviceToEntity).collect(toList());
    }

    private MessageDeviceEntity mapMessageDeviceToEntity(MessageDevice messageDevice) {
        MessageDeviceEntity entity = new MessageDeviceEntity();
        entity.setName(messageDevice.getName());
        return entity;
    }

    public DeviceGroup mapEntityToDeviceGroup(DeviceGroupEntity entity) {
        return new DeviceGroup(entity.getName(), toMessageDevices(entity.getMessageDevices()));
    }

    private List<MessageDevice> toMessageDevices(List<MessageDeviceEntity> messageDevices) {
        return messageDevices.stream().map(entity -> new MessageDevice(entity.name)).collect(toList());
    }
}
