package de.eurofunk.broker;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Exposes static spring context to easily communicate with Message Broker.
 *
 * This would be implemented via some messaging protocol in real life scenario.
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        SpringContext.context = context;
    }
}
