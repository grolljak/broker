package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MessageCenter {

    private Broker broker;
    private Map<String, DeviceGroup> deviceGroups;

    public MessageCenter(Broker broker, Map<String, DeviceGroup> deviceGroups) {
        this.broker = broker;
        this.deviceGroups = deviceGroups;
    }

    public void registerMessageDevice(MessageDevice device) {
        //create message device in db

        //add it into message devices
        broker.addQueue(device.getName()); //move to separate service
    }

    public void removeMessageDevice(MessageDevice device) {
        //delete from db

        broker.removeQueue(device.getName());
    }

    public void registerDeviceGroup(DeviceGroup group) {
        //create device group in db

        //add it into device groups
        deviceGroups.put(group.getName(), group);
    }

    public void removeDeviceGroup(DeviceGroup group) {
        //delete from db

        deviceGroups.remove(group);
    }

}
