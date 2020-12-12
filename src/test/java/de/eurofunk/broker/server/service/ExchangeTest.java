package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.component.Exchange;
import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.domain.MyMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
    void directExchangeTest() {
        //given
        MessageQueue beeper = mockMessageQueue("beeper");
        MessageQueue notABeeper = mockMessageQueue("not_a_beeper");
        MyMessage message = new MyMessage("beeper", DIRECT, "beep");

        //when
        exchange.send(message);

        //then
        assertEquals(1, beeper.size());
        assertEquals(0, notABeeper.size());
    }

    private MessageQueue mockMessageQueue(String name) {
        MessageQueue beeper = new MessageQueue(name);
        when(queueService.getQueue(name)).thenReturn(beeper);
        return beeper;
    }

    @Test
    void directExchangeMultipleMessagesTest() {
        //given
        MyMessage message1 = new MyMessage("beeper", DIRECT, "beep");
        MyMessage message2 = new MyMessage("beeper", DIRECT, "boop");
        MyMessage message3 = new MyMessage("not_a_beeper", DIRECT, "doot");

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
    void directExchangeCorrectOrderTest() {
        //given
        MyMessage message1 = new MyMessage("beeper", DIRECT, "beep");
        MyMessage message2 = new MyMessage("beeper", DIRECT, "boop");
        MyMessage message3 = new MyMessage("beeper", DIRECT, "beep boop");

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
    void broadcastExchangeTest() {
        //given
        MyMessage message1 = new MyMessage(BROADCAST, "beep");
        MyMessage message2 = new MyMessage(BROADCAST, "boop");
        MyMessage message3 = new MyMessage(BROADCAST, "beep boop");

        List<MessageDevice> devices = Arrays.asList(
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

        when(queueService.getAllQueues()).thenReturn(Arrays.asList(beeperA, beeperB, beeperC, notABeeper));

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
}