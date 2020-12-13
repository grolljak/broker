package de.eurofunk.broker.server.component;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.service.DeviceGroupService;
import de.eurofunk.broker.server.service.LoggingService;
import de.eurofunk.broker.server.service.MessageDeviceService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageCenter {

    private MessageDeviceService messageDeviceService;
    private DeviceGroupService deviceGroupService;
    private LoggingService loggingService;

    public MessageCenter(MessageDeviceService messageDeviceService, DeviceGroupService deviceGroupService, LoggingService loggingService) {
        this.messageDeviceService = messageDeviceService;
        this.deviceGroupService = deviceGroupService;
        this.loggingService = loggingService;
    }

    public void registerMessageDevice(MessageDevice device) {
        messageDeviceService.registerMessageDevice(device);
    }

    public void removeMessageDevice(String name) {
        messageDeviceService.removeMessageDevice(name);
    }

    public void registerDeviceGroup(DeviceGroup group) {
        deviceGroupService.registerDeviceGroup(group);
    }

    public void removeDeviceGroup(String name) {
        deviceGroupService.removeDeviceGroup(name);
    }

    public List<MessageDevice> getRegisteredMessageDevices() {
        return messageDeviceService.getAllMessageDevices();
    }

    public void addKeyword(String keyword) {
        loggingService.addKeyword(keyword);
    }

    public void removeKeyword(String keyword) {
        loggingService.removeKeyword(keyword);
    }


}
