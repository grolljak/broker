package de.eurofunk.broker.server.domain;

import java.util.ArrayList;
import java.util.List;

public class DeviceGroup {

    private final String name;
    private List<MessageDevice> messageDevices = new ArrayList<>();

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

    public List<MessageDevice> getMessageDevices() {
        return messageDevices;
    }

    public String getName() {
        return name;
    }
}
