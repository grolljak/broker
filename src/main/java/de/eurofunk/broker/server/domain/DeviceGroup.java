package de.eurofunk.broker.server.domain;

import java.util.ArrayList;
import java.util.List;

public class DeviceGroup {
    List<MessageDevice> messageDevices = new ArrayList<>();
    private final String name;

    public DeviceGroup(String name) {
        this.name = name;
    }

    public void assignMessageDevice(MessageDevice device) {
        messageDevices.add(device);
    }

    public void assignMessageDevices(List<MessageDevice> devices) {
        messageDevices.addAll(devices);
    }

    public void unassignMessageDevice(MessageDevice device) {
        messageDevices.remove(device);
    }
}
