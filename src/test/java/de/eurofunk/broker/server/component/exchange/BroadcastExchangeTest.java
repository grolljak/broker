package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.domain.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.eurofunk.broker.server.domain.MessageSemantic.BROADCAST;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BroadcastExchangeTest extends ExchangeTest {

    @Test
    @DisplayName("when sending broadcast messages, routing should work")
    void broadcastExchangeTest() {
        //given
        Message message1 = new Message(BROADCAST, "beep");
        Message message2 = new Message(BROADCAST, "boop");
        Message message3 = new Message(BROADCAST, "beep boop");

        //when
        broadcastExchange.send(message1);
        broadcastExchange.send(message2);
        broadcastExchange.send(message3);

        //then
        verify(queueService, times(1)).addMessageToAllQueues(message1);
        verify(queueService, times(1)).addMessageToAllQueues(message2);
        verify(queueService, times(1)).addMessageToAllQueues(message3);
    }
}