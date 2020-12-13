package de.eurofunk.broker.server.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Device Group class, groups message devices together, so they can receive messages.
 */
public class DeviceGroup {

    private final String name;
    private List<MessageDevice> messageDevices = new ArrayList<>();

    public DeviceGroup(String name) {
        this.name = name;
    }

    public DeviceGroup(String name, List<MessageDevice> messageDevices) {
        this.name = name;
        this.messageDevices = messageDevices;
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
