package de.eurofunk.broker;

import de.eurofunk.broker.server.component.MessageCenter;
import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MyMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static de.eurofunk.broker.server.domain.MessageSemantic.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class BrokerApplicationTests extends BaseIntegrationTest {

    @Autowired
    MessageCenter messageCenter;

    @Test
    @DisplayName("when running empty test, application context should load with no issues")
    void contextLoads() {
    }

    @Test
    @DisplayName("when sending direct message, targeted device should receive message")
    void directMessageIntegrationTest() {
        //given
        MessageDevice messageDeviceA = registerNewMessageDevice("a");

        //when
        String sendMessage = "Hello World!";
        messageDeviceA.send(new MyMessage("a", DIRECT, sendMessage));
        String receivedMessage = messageDeviceA.receive();

        //then
        assertEquals(sendMessage, receivedMessage);
    }

    @Test
    @DisplayName("when sending direct message and then removing targeted device, it should not be found")
    void directMessageWithRemovalIntegrationTest() {
        //given
        MessageDevice messageDeviceA = registerNewMessageDevice("a");

        //when
        String sendMessage = "Hello World!";
        messageDeviceA.send(new MyMessage("a", DIRECT, sendMessage));

        messageCenter.removeMessageDevice(messageDeviceA.getName());

        //then
        assertThrows(RuntimeException.class, messageDeviceA::receive);
    }

    @Test
    @DisplayName("when sending multicast message and then removing targeted group, message should be received")
    void multicastMessageWithRemovalIntegrationTest() {
        //given
        MessageDevice messageDevice = registerNewMessageDevice("device");
        DeviceGroup deviceGroup = registerNewDeviceGroup("a", List.of(messageDevice));

        //when
        String sendMessage = "Hello World!";
        messageDevice.send(new MyMessage("a", MULTICAST, sendMessage));

        messageCenter.removeDeviceGroup(deviceGroup.getName());

        //then
        assertEquals(sendMessage, messageDevice.receive());
    }

    @Test
    @DisplayName("when sending multicast message, all devices in group should receive message")
    void multicastMessageIntegrationTest() {
        //given
        MessageDevice messageDeviceA = registerNewMessageDevice("a");
        MessageDevice messageDeviceB = registerNewMessageDevice("b");

        registerNewDeviceGroup("a", List.of(messageDeviceA, messageDeviceB));

        //when
        String sendMessage = "Hello World!";
        messageDeviceA.send(new MyMessage("a", MULTICAST, sendMessage));

        //then
        String receivedMessageA = messageDeviceA.receive();
        String receivedMessageB = messageDeviceB.receive();
        assertEquals(sendMessage, receivedMessageA);
        assertEquals(receivedMessageA, receivedMessageB);
    }

    @Test
    @DisplayName("when sending multicast message, device outside of the group should have no message")
    void multicastNoMessageIntegrationTest() {
        //given
        MessageDevice messageDeviceA = registerNewMessageDevice("a");
        MessageDevice messageDeviceB = registerNewMessageDevice("b");
        MessageDevice messageDeviceC = registerNewMessageDevice("c");

        registerNewDeviceGroup("a", List.of(messageDeviceA, messageDeviceB));

        //when
        String sendMessage = "Hello World!";
        messageDeviceA.send(new MyMessage("a", MULTICAST, sendMessage));

        //then
        assertThrows(IllegalStateException.class, messageDeviceC::receive);
    }

    @Test
    @DisplayName("when sending broadcast message, all devices should have message")
    void broadcastMessageIntegrationTest() {
        //given
        MessageDevice messageDeviceA = registerNewMessageDevice("a");
        MessageDevice messageDeviceB = registerNewMessageDevice("b");
        MessageDevice messageDeviceC = registerNewMessageDevice("c");

        registerNewDeviceGroup("a", List.of(messageDeviceA, messageDeviceB));

        //when
        String sendMessage = "Hello World!";
        messageDeviceA.send(new MyMessage("a", BROADCAST, sendMessage));

        //then
        String receivedMessageA = messageDeviceA.receive();
        String receivedMessageB = messageDeviceB.receive();
        String receivedMessageC = messageDeviceC.receive();

        assertEquals(sendMessage, receivedMessageA);
        assertEquals(receivedMessageA, receivedMessageB);
        assertEquals(receivedMessageB, receivedMessageC);
    }

    private DeviceGroup registerNewDeviceGroup(String name, List<MessageDevice> messageDevices) {
        DeviceGroup deviceGroup = new DeviceGroup(name);
        deviceGroup.assignMessageDevices(messageDevices);
        messageCenter.registerDeviceGroup(deviceGroup);
        return deviceGroup;
    }

    private MessageDevice registerNewMessageDevice(String name) {
        MessageDevice messageDevice = new MessageDevice(name);
        messageCenter.registerMessageDevice(messageDevice);
        return messageDevice;
    }
}
