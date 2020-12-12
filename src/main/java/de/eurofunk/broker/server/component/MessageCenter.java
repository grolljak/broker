package de.eurofunk.broker.server.component;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.service.DeviceGroupService;
import de.eurofunk.broker.server.service.MessageDeviceService;
import org.springframework.stereotype.Component;

@Component
public class MessageCenter {

    private MessageDeviceService messageDeviceService;
    private DeviceGroupService deviceGroupService;

    public MessageCenter(MessageDeviceService messageDeviceService, DeviceGroupService deviceGroupService) {
        this.messageDeviceService = messageDeviceService;
        this.deviceGroupService = deviceGroupService;
    }

    public void registerMessageDevice(MessageDevice device) {
       messageDeviceService.registerMessageDevice(device);
    }

    public void removeMessageDevice(MessageDevice device) {
        messageDeviceService.removeMessageDevice(device);
    }

    public void registerDeviceGroup(DeviceGroup group) {
        deviceGroupService.registerDeviceGroup(group);
    }

    public void removeDeviceGroup(DeviceGroup group) {
        deviceGroupService.removeDeviceGroup(group);
    }

}
