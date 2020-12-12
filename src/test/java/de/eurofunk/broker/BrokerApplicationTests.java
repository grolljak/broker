package de.eurofunk.broker;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MyMessage;
import de.eurofunk.broker.server.service.MessageCenter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BrokerApplicationTests {

    @Autowired
    MessageCenter messageCenter;

    @Test
    void contextLoads() {
    }

    @Test
    void messageIntegrationTest() {

        //given
        DeviceGroup deviceGroupA = new DeviceGroup("DGA");

        MessageDevice messageDeviceA = new MessageDevice("MDA");
        MessageDevice messageDeviceB = new MessageDevice("MDB");

        deviceGroupA.assignMessageDevice(messageDeviceA); //todo: add a list
        deviceGroupA.assignMessageDevice(messageDeviceB);

        messageCenter.registerMessageDevice(messageDeviceA);
        messageCenter.registerMessageDevice(messageDeviceB);
        messageCenter.registerDeviceGroup(deviceGroupA);

        //when
        String sendMessage = "Hello World!";
        messageDeviceA.send(new MyMessage("mda", sendMessage));
        String receivedMessage = messageDeviceB.receive();

        //then
        assertEquals(sendMessage, receivedMessage);
    }
}
