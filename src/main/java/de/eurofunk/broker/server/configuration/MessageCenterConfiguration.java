package de.eurofunk.broker.server.configuration;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MessageQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MessageCenterConfiguration {

    @Bean
    List<DeviceGroup> getDeviceGroups() {
        return new ArrayList<>(); //todo: fetch data from db on startup
    }

    @Bean
    List<MessageDevice> getMessageDevices() {
        return new ArrayList<>(); //todo: fetch data from db on startup
    }

    @Bean
    Map<String, MessageQueue> getQueues() {
        return new HashMap<>(); //todo: should be loaded from message devices
    }
}
