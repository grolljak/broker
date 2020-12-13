package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.domain.DeviceGroup;
import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.domain.MessageDevice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static de.eurofunk.broker.server.domain.MessageSemantic.MULTICAST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MulticastExchangeTest extends ExchangeTest {

    @Test
    @DisplayName("when sending multicast messages, routing should work")
    void multicastExchangeTest() {
        //given
        Message message = new Message("beeper", MULTICAST, "beep");

        MessageDevice beeperA = new MessageDevice("beeper_a");
        MessageDevice beeperB = new MessageDevice("beeper_b");
        MessageDevice beeperC = new MessageDevice("beeper_c");
        MessageDevice notABeeper = new MessageDevice("not_a_beeper");

        when(deviceGroupService.getGroup(message.getRoutingKey())).thenReturn(new DeviceGroup("beepers", List.of(beeperA, beeperB, beeperC)));

        //when
        multicastExchange.send(message);

        //then
        ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(List.class);
        verify(queueService, times(1)).addMessageToQueues(eq(message), captor.capture());
        assertEquals(3, captor.getValue().size());
    }

}