package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.component.Exchange;
import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.eurofunk.broker.server.domain.MessageSemantic.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExchangeTest {

    private Exchange exchange;
    private DeviceGroupService deviceGroupService;
    private QueueService queueService;

    @BeforeEach
    void setUp() {
        queueService = mock(QueueService.class);
        deviceGroupService = mock(DeviceGroupService.class);

        exchange = new Exchange(queueService, deviceGroupService);
    }

    @Test
    @DisplayName("when sending direct message, routing should work")
    void directExchangeTest() {
        //given
        MessageQueue beeper = mockMessageQueue("beeper");
        MessageQueue notABeeper = mockMessageQueue("not_a_beeper");
        Message message = new Message("beeper", DIRECT, "beep");

        //when
        exchange.send(message);

        //then
        assertEquals(1, beeper.size());
        assertEquals(0, notABeeper.size());
    }

    @Test
    @DisplayName("when sending multiple direct messages, routing should work")
    void directExchangeMultipleMessagesTest() {
        //given
        Message message1 = new Message("beeper", DIRECT, "beep");
        Message message2 = new Message("beeper", DIRECT, "boop");
        Message message3 = new Message("not_a_beeper", DIRECT, "doot");

        MessageQueue beeper = mockMessageQueue("beeper");
        MessageQueue notABeeper = mockMessageQueue("not_a_beeper");

        //when
        exchange.send(message1);
        exchange.send(message2);
        exchange.send(message3);

        //then
        assertEquals(2, beeper.size());
        assertEquals(1, notABeeper.size());
    }

    @Test
    @DisplayName("when sending multiple direct messages, correct order of messages should be followed")
    void directExchangeCorrectOrderTest() {
        //given
        Message message1 = new Message("beeper", DIRECT, "beep");
        Message message2 = new Message("beeper", DIRECT, "boop");
        Message message3 = new Message("beeper", DIRECT, "beep boop");

        MessageQueue beeper = mockMessageQueue("beeper");

        //when
        exchange.send(message1);
        exchange.send(message2);
        exchange.send(message3);

        //then
        assertEquals("beep", beeper.get());
        assertEquals("boop", beeper.get());
        assertEquals("beep boop", beeper.get());
    }

    @Test
    @DisplayName("when sending multicast messages, routing should work")
    void multicastExchangeTest() {
        //given
        Message message1 = new Message("beeper", MULTICAST, "beep");
        Message message2 = new Message("beeper", MULTICAST, "boop");
        Message message3 = new Message("beeper", MULTICAST, "beep boop");

        List<MessageDevice> devices = List.of(
                new MessageDevice("beeper_a"),
                new MessageDevice("beeper_b"),
                new MessageDevice("beeper_c")
        );

        DeviceGroup beepers = new DeviceGroup("beeper");
        beepers.assignMessageDevices(devices);

        when(deviceGroupService.getGroup("beeper")).thenReturn(beepers);

        MessageQueue beeperA = mockMessageQueue("beeper_a");
        MessageQueue beeperB = mockMessageQueue("beeper_b");
        MessageQueue beeperC = mockMessageQueue("beeper_c");
        MessageQueue notABeeper = mockMessageQueue("not_a_beeper");

        //when
        exchange.send(message1);
        exchange.send(message2);
        exchange.send(message3);

        //then
        assertEquals(3, beeperA.size());
        assertEquals(3, beeperB.size());
        assertEquals(3, beeperC.size());
        assertEquals(0, notABeeper.size());
    }

    @Test
    @DisplayName("when sending broadcast messages, routing should work")
    void broadcastExchangeTest() {
        //given
        Message message1 = new Message(BROADCAST, "beep");
        Message message2 = new Message(BROADCAST, "boop");
        Message message3 = new Message(BROADCAST, "beep boop");

        List<MessageDevice> devices = List.of(
                new MessageDevice("beeper_a"),
                new MessageDevice("beeper_b"),
                new MessageDevice("beeper_c")
        );

        DeviceGroup beepers = new DeviceGroup("beeper");
        beepers.assignMessageDevices(devices);
        when(deviceGroupService.getGroup("beeper")).thenReturn(beepers);

        MessageQueue beeperA = mockMessageQueue("beeper_a");
        MessageQueue beeperB = mockMessageQueue("beeper_b");
        MessageQueue beeperC = mockMessageQueue("beeper_c");
        MessageQueue notABeeper = mockMessageQueue("not_a_beeper");

        when(queueService.getAllQueues()).thenReturn(List.of(beeperA, beeperB, beeperC, notABeeper));

        //when
        exchange.send(message1);
        exchange.send(message2);
        exchange.send(message3);

        //then
        assertEquals(3, beeperA.size());
        assertEquals(3, beeperB.size());
        assertEquals(3, beeperC.size());
        assertEquals(3, notABeeper.size());
    }

    private MessageQueue mockMessageQueue(String name) {
        MessageQueue beeper = new MessageQueue(name);
        when(queueService.getQueue(name)).thenReturn(beeper);
        return beeper;
    }
}