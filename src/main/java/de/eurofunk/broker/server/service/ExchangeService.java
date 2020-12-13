package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.Exchange;
import de.eurofunk.broker.server.component.exchange.ExchangeFactory;
import de.eurofunk.broker.server.domain.Message;
import org.springframework.stereotype.Component;

@Component
public class ExchangeService {

    private ExchangeFactory exchangeFactory;

    public ExchangeService(ExchangeFactory exchangeFactory) {
        this.exchangeFactory = exchangeFactory;
    }

    public void send(Message message) {
        Exchange exchange = exchangeFactory.getExchange(message.getSemantic());
        exchange.send(message);
    }
}
