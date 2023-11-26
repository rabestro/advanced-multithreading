package epam.task3;

import java.util.Optional;

public class Consumer extends Thread {
    private final MessageBus bus;
    private final String topic;

    public Consumer(MessageBus bus, String topic) {
        this.bus = bus;
        this.topic = topic;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Optional<Message> message = bus.consumeMessage(topic);
            message.ifPresent(m -> System.out.println("Consumer " + this.getId() + " consumed: " + m.getPayload()));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
