package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.eurofunk.broker.server.domain.MessageSemantic.DIRECT;
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
    void fanoutExchangeTest() {

    }

    @Test
    void groupExchangeTest() {

    }
}