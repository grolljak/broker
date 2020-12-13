package de.eurofunk.broker.server.persistance.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "DeviceGroup")
public class DeviceGroupEntity {
    @Id
    public String name;

    @ManyToMany(cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinTable(
            name = "MessageDevice_DeviceGroup_Association",
            joinColumns = @JoinColumn(name = "DeviceGroup_Name"),
            inverseJoinColumns = @JoinColumn(name = "MessageDevice_Name"))
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
