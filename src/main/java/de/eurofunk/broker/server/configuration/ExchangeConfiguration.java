package de.eurofunk.broker.server.configuration;

import de.eurofunk.broker.server.Exchange;
import de.eurofunk.broker.server.component.exchange.BroadcastExchange;
import de.eurofunk.broker.server.component.exchange.DirectExchange;
import de.eurofunk.broker.server.component.exchange.ExchangeFactory;
import de.eurofunk.broker.server.component.exchange.MulticastExchange;
import de.eurofunk.broker.server.service.DeviceGroupService;
import de.eurofunk.broker.server.service.QueueService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class ExchangeConfiguration {

    @Bean
    public ServiceLocatorFactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ExchangeFactory.class);
        return factoryBean;
    }

    @Bean(name = "direct")
    @Scope(scopeName = SCOPE_PROTOTYPE)
    public DirectExchange getDirectExchange(QueueService service) {
        return new DirectExchange(service);
    }

    @Bean(name = "multicast")
    @Scope(scopeName = SCOPE_PROTOTYPE)
    public MulticastExchange getMulticastExchange(DeviceGroupService groupService, QueueService queueService) {
        return new MulticastExchange(groupService, queueService);
    }

    @Bean(name = "broadcast")
    @Scope(scopeName = SCOPE_PROTOTYPE)
    public BroadcastExchange getBroadcastExchange(QueueService queueService) {
        return new BroadcastExchange(queueService);
    }

}
