package de.eurofunk.broker.server.persistance.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.DETACH;

@Entity
@Table(name = "MessageDevice")
public class MessageDeviceEntity {
    @Id
    public String name;

    @ManyToMany(cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinTable(
            name = "MessageDevice_DeviceGroup_Association",
            joinColumns = @JoinColumn(name = "MessageDevice_Name"),
            inverseJoinColumns = @JoinColumn(name = "DeviceGroup_Name"))
    List<DeviceGroupEntity> deviceGroups = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeviceGroupEntity> getDeviceGroups() {
        return deviceGroups;
    }

    public void setDeviceGroups(List<DeviceGroupEntity> deviceGroups) {
        this.deviceGroups = deviceGroups;
    }
}
