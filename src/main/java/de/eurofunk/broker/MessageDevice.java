package de.eurofunk.broker;

public class MessageDevice implements MessageConsumer, MessageProducer {

    @Override
    public MyMessage receive() {
        //broker receive()
        return null;
    }

    @Override
    public void send() {
       // broker.send()
    }
}
