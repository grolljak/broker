package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.domain.MessageQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.eurofunk.broker.server.domain.MessageSemantic.BROADCAST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BroadcastExchangeTest extends ExchangeTest {

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
        broadcastExchange.send(message1);
        broadcastExchange.send(message2);
        broadcastExchange.send(message3);

        //then
        assertEquals(3, beeperA.size());
        assertEquals(3, beeperB.size());
        assertEquals(3, beeperC.size());
        assertEquals(3, notABeeper.size());
    }
}