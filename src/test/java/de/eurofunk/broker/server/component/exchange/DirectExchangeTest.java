package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.domain.Message;
import de.eurofunk.broker.server.domain.MessageQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.eurofunk.broker.server.domain.MessageSemantic.DIRECT;
import static org.junit.jupiter.api.Assertions.*;

class DirectExchangeTest extends ExchangeTest {

    @Test
    @DisplayName("when sending direct message, routing should work")
    void directExchangeTest() {
        //given
        MessageQueue beeper = mockMessageQueue("beeper");
        MessageQueue notABeeper = mockMessageQueue("not_a_beeper");
        Message message = new Message("beeper", DIRECT, "beep");

        //when
        directExchange.send(message);

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
        directExchange.send(message1);
        directExchange.send(message2);
        directExchange.send(message3);

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
        directExchange.send(message1);
        directExchange.send(message2);
        directExchange.send(message3);

        //then
        assertEquals("beep", beeper.get());
        assertEquals("boop", beeper.get());
        assertEquals("beep boop", beeper.get());
    }
}