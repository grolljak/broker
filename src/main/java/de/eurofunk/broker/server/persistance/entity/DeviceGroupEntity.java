package de.eurofunk.broker.server.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DeviceGroup")
public class DeviceGroupEntity {
    @Id
    public String name;

    @OneToMany
    List<MessageDeviceEntity> messageDevices = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MessageDeviceEntity> getMessageDevices() {
        return messageDevices;
    }

    public void setMessageDevices(List<MessageDeviceEntity> messageDevices) {
        this.messageDevices = messageDevices;
    }
}
