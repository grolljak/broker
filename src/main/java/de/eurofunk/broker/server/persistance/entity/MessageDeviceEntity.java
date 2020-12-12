package de.eurofunk.broker.server.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MessageDevice")
public class MessageDeviceEntity {
    @Id
    public String name;
}
