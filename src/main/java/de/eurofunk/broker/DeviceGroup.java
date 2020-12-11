package de.eurofunk.broker;

import java.util.List;

public class DeviceGroup {
    List<MessageDevice> messageDevices;

    public void assignMessageDevice(MessageDevice device) {
        messageDevices.add(device);
    }

    public void unassignMessageDevice(MessageDevice device) {
        messageDevices.remove(device);
    }
}
