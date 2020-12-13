package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.Exchange;
import de.eurofunk.broker.server.domain.MessageSemantic;

public interface ExchangeFactory {
    Exchange getExchange(MessageSemantic exchangeType);
}
