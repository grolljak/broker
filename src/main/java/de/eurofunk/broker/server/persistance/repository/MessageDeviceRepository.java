package de.eurofunk.broker.server.persistance.repository;

import de.eurofunk.broker.server.persistance.entity.MessageDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDeviceRepository extends JpaRepository<MessageDeviceEntity, String> {
}
