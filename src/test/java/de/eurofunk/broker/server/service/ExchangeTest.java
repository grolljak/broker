package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
        MyMessage message = new MyMessage("md_beeper", "beep");
        queues.put("beeper", new MessageQueue("beeper"));
        queues.put("not_a_beeper", new MessageQueue("not_a_beeper"));

        //when
        exchange.send(message);

        //then
        assertEquals(1, queues.get("beeper").size());
        assertEquals(0, queues.get("not_a_beeper").size());
    }

    @Test
    void fanoutExchangeTest() {

    }

    @Test
    void groupExchangeTest() {

    }
}