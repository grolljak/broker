package de.eurofunk.broker.configuration;

import de.eurofunk.broker.DeviceGroup;
import de.eurofunk.broker.MessageDevice;
import de.eurofunk.broker.MessageQueue;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MessageCenterConfiguration {

    @Bean
    List<DeviceGroup> getDeviceGroups() {
        return new ArrayList<>(); //fetch data from db on startup
    }

    @Bean
    List<MessageDevice> getMessageDevices() {
        return new ArrayList<>(); //fetch data from db on startup
    }

    @Bean
    List<MessageQueue> getQueues() {
        return new ArrayList<>(); //should be loaded from message devices
    }
}
