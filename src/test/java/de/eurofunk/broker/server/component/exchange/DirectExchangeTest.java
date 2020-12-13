package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.domain.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.eurofunk.broker.server.domain.MessageSemantic.DIRECT;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DirectExchangeTest extends ExchangeTest {

    @Test
    @DisplayName("when sending direct message, routing should work")
    void directExchangeTest() {
        //given
        Message message = new Message("beeper", DIRECT, "beep");

        //when
        directExchange.send(message);

        //then
        verify(queueService, times(1)).addMessageToQueues(eq(message), anyList());
    }

    @Test
    @DisplayName("when sending multiple direct messages, routing should work")
    void directExchangeMultipleMessagesTest() {
        //given
        Message message1 = new Message("beeper", DIRECT, "beep");
        Message message2 = new Message("beeper", DIRECT, "boop");
        Message message3 = new Message("not_a_beeper", DIRECT, "doot");

        //when
        directExchange.send(message1);
        directExchange.send(message2);
        directExchange.send(message3);

        //then
        verify(queueService, times(1)).addMessageToQueues(eq(message1), anyList());
        verify(queueService, times(1)).addMessageToQueues(eq(message2), anyList());
        verify(queueService, times(1)).addMessageToQueues(eq(message3), anyList());

    }
}