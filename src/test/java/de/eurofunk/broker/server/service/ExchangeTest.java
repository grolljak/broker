package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static de.eurofunk.broker.server.domain.MessageSemantic.DIRECT;
import static de.eurofunk.broker.server.domain.MessageSemantic.MULTICAST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExchangeTest {

    Exchange exchange;
    List<DeviceGroup> deviceGroups;
    Map<String, MessageQueue> queues;

    @BeforeEach
    void setUp() {
        deviceGroups = new ArrayList<>();
        queues = new HashMap<>();

        exchange = new Exchange(queues, deviceGroups);
    }

    @Test
    void directExchangeTest() {
        //given
        MyMessage message = new MyMessage("beeper", DIRECT, "beep");
        queues.put("beeper", new MessageQueue("beeper"));
        queues.put("not_a_beeper", new MessageQueue("not_a_beeper"));

        //when
        exchange.send(message);

        //then
        assertEquals(1, queues.get("beeper").size());
        assertEquals(0, queues.get("not_a_beeper").size());
    }

    @Test
    void directExchangeMultipleMessagesTest() {
        //given
        MyMessage message1 = new MyMessage("beeper", DIRECT, "beep");
        MyMessage message2 = new MyMessage("beeper", DIRECT, "boop");
        MyMessage message3 = new MyMessage("not_a_beeper", DIRECT, "doot");
        queues.put("beeper", new MessageQueue("beeper"));
        queues.put("not_a_beeper", new MessageQueue("not_a_beeper"));

        //when
        exchange.send(message1);
        exchange.send(message2);
        exchange.send(message3);

        //then
        assertEquals(2, queues.get("beeper").size());
        assertEquals(1, queues.get("not_a_beeper").size());
    }

    @Test
    void directExchangeCorrectOrderTest() {
        //given
        MyMessage message1 = new MyMessage("beeper", DIRECT, "beep");
        MyMessage message2 = new MyMessage("beeper", DIRECT, "boop");
        MyMessage message3 = new MyMessage("beeper", DIRECT, "beep boop");
        queues.put("beeper", new MessageQueue("beeper"));

        //when
        exchange.send(message1);
        exchange.send(message2);
        exchange.send(message3);

        //then
        assertEquals("beep", queues.get("beeper").get());
        assertEquals("boop", queues.get("beeper").get());
        assertEquals("beep boop", queues.get("beeper").get());
    }

    @Test
    void multicastExchangeTest() {
        //given
        MyMessage message1 = new MyMessage("beeper", MULTICAST, "beep");
        MyMessage message2 = new MyMessage("beeper", MULTICAST, "boop");
        MyMessage message3 = new MyMessage("beeper", MULTICAST, "beep boop");

        List<MessageDevice> devices = Arrays.asList(
                new MessageDevice("beeper_a"),
                new MessageDevice("beeper_b"),
                new MessageDevice("beeper_c")
        );

        DeviceGroup beepers = new DeviceGroup("beeper");
        beepers.assignMessageDevices(devices);
        deviceGroups.add(beepers);

        queues.put("beeper_a", new MessageQueue("beeper_a"));
        queues.put("beeper_b", new MessageQueue("beeper_b"));
        queues.put("beeper_c", new MessageQueue("beeper_c"));
        queues.put("not_a_beeper", new MessageQueue("not_a_beeper"));

        //when
        exchange.send(message1);
        exchange.send(message2);
        exchange.send(message3);

        //then
        assertEquals(3, queues.get("beeper_a").size());
        assertEquals(3, queues.get("beeper_b").size());
        assertEquals(3, queues.get("beeper_c").size());
        assertEquals(0, queues.get("not_a_beeper").size());
    }

    @Test
    void groupExchangeTest() {

    }
}