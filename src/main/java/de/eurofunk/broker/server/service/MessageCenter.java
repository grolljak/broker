package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageCenter {

    private Broker broker;
    private List<DeviceGroup> deviceGroups;
    private List<MessageDevice> messageDevices;

    public MessageCenter(Broker broker, List<DeviceGroup> deviceGroups, List<MessageDevice> messageDevices) { //groups and devices might be in separate services
        this.broker = broker;
        this.deviceGroups = deviceGroups;
        this.messageDevices = messageDevices;
    }

    public void registerMessageDevice(MessageDevice device) {
        //create message device in db

        //add it into message devices
        messageDevices.add(device);
    }

    public void registerDeviceGroup(DeviceGroup group) {
        //create device group in db

        //add it into device groups
        deviceGroups.add(group);
    }

    public void removeMessageDevice(MessageDevice device) {
        //delete from db

        messageDevices.remove(device);
    }

    public void removeDeviceGroup(DeviceGroup group) {
        //delete from db

        deviceGroups.remove(group);
    }

}