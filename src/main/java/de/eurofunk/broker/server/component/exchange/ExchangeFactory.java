package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.Exchange;
import de.eurofunk.broker.server.domain.MessageSemantic;

/**
 * Exchange factory interface that provides method for exchange creation.
 */
public interface ExchangeFactory {

    /**
     * Creates implementation based on given exchange type.
     *
     * @param exchangeType type defined by message semantic
     * @return exchange implementation
     */
    Exchange getExchange(MessageSemantic exchangeType);
}
