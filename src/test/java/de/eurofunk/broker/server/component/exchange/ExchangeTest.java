package de.eurofunk.broker.server.component.exchange;

import de.eurofunk.broker.server.domain.MessageQueue;
import de.eurofunk.broker.server.service.DeviceGroupService;
import de.eurofunk.broker.server.service.QueueService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExchangeTest {

    protected DeviceGroupService deviceGroupService;
    protected QueueService queueService;
    protected DirectExchange directExchange;
    protected MulticastExchange multicastExchange;
    protected BroadcastExchange broadcastExchange;

    @BeforeEach
    void setUp() {
        queueService = mock(QueueService.class);
        deviceGroupService = mock(DeviceGroupService.class);

        directExchange = new DirectExchange(queueService);
        multicastExchange = new MulticastExchange(deviceGroupService, queueService);
        broadcastExchange = new BroadcastExchange(queueService);
    }

    protected MessageQueue mockMessageQueue(String name) {
        MessageQueue beeper = new MessageQueue(name);
        when(queueService.getQueue(name)).thenReturn(beeper);
        return beeper;
    }
}