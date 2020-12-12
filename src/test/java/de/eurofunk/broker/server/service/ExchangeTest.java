package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeTest {

    Exchange exchange;
    List<DeviceGroup> deviceGroups;
    List<MessageQueue> queues;

    @BeforeEach
    void setUp() {
        deviceGroups = new ArrayList<>();
        queues = new ArrayList<>();

        exchange = new Exchange(queues, deviceGroups);
    }

    @Test
    void directExchangeTest() {
        //given
        MyMessage message = new MyMessage("md_beeper", "beep");
        queues.add(new MessageQueue("beeper"));
        queues.add(new MessageQueue("not_a_beeper"));
        //when
        exchange.send(message);

        //then
        assertEquals(1, queues.get(0).size()); //todo: change to map for easier retrieval of queues
        assertEquals(0, queues.get(1).size());
    }

    @Test
    void fanoutExchangeTest() {

    }

    @Test
    void groupExchangeTest() {

    }
}