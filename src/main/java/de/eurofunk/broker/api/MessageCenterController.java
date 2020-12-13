package de.eurofunk.broker.api;

import de.eurofunk.broker.server.component.MessageCenter;
import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MyMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static de.eurofunk.broker.server.domain.MessageSemantic.*;
import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("api/message-center")
public class MessageCenterController {

    MessageCenter messageCenter;

    public MessageCenterController(MessageCenter messageCenter) {
        this.messageCenter = messageCenter;
    }

    @PostMapping("/setup")
    @ResponseStatus(HttpStatus.OK)
    public void setup() {
        MessageDevice messageDeviceA = registerNewMessageDevice("a");
        MessageDevice messageDeviceB = registerNewMessageDevice("b");
        MessageDevice messageDeviceC = registerNewMessageDevice("c");
        MessageDevice messageDeviceD = registerNewMessageDevice("d");

        registerNewDeviceGroup("a", List.of(messageDeviceA, messageDeviceB, messageDeviceC));
    }


    @PostMapping("/sendDirect")
    @ResponseStatus(HttpStatus.OK)
    public void sendDirectMessage(@RequestParam String from, @RequestParam String routingKey, @RequestParam String message) {
        getMessageDevice(from).send(new MyMessage(routingKey, DIRECT, message));
    }

    @PostMapping("/sendMulticast")
    @ResponseStatus(HttpStatus.OK)
    public void sendMulticastMessage(@RequestParam String from, @RequestParam String routingKey, @RequestParam String message) {
        getMessageDevice(from).send(new MyMessage(routingKey, MULTICAST, message));
    }

    @PostMapping("/sendBroadcast")
    @ResponseStatus(HttpStatus.OK)
    public void sendBroadcastMessage(@RequestParam String from, @RequestParam String message) {
        getMessageDevice(from).send(new MyMessage(BROADCAST, message));
    }

    @GetMapping("/receive")
    public String sendMessage(@RequestParam String from) {
        return getMessageDevice(from).receive();
    }

    @DeleteMapping("/removeMessageDevice")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMessageDevice(@RequestParam String name) {
        messageCenter.removeMessageDevice(name);
    }

    @DeleteMapping("/removeDeviceGroup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDeviceGroup(@RequestParam String name) {
        messageCenter.removeDeviceGroup(name);
    }

    private MessageDevice getMessageDevice(@RequestParam String from) {
        MessageDevice messageDevice = getMessageDevicesMap().get(from);
        if (messageDevice == null) throw new IllegalArgumentException("Message device not registered.");
        return messageDevice;
    }

    private Map<String, MessageDevice> getMessageDevicesMap() {
        return messageCenter.getRegisteredMessageDevices().stream().collect(toMap(MessageDevice::getName, device -> device));
    }

    private void registerNewDeviceGroup(String name, List<MessageDevice> messageDevices) {
        DeviceGroup deviceGroup = new DeviceGroup(name);
        deviceGroup.assignMessageDevices(messageDevices);
        messageCenter.registerDeviceGroup(deviceGroup);
    }

    private MessageDevice registerNewMessageDevice(String name) {
        MessageDevice messageDevice = new MessageDevice(name);
        messageCenter.registerMessageDevice(messageDevice);
        return messageDevice;
    }
}
