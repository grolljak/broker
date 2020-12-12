package de.eurofunk.broker.server.persistance.repository;

import de.eurofunk.broker.server.persistance.entity.DeviceGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceGroupRepository extends JpaRepository<DeviceGroupEntity, String> {
}
