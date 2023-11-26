package epam.task3;

import java.util.Random;

public class Producer extends Thread {
    private static final String[] TOPICS = {"topic1", "topic2", "topic3"};
    private static final Random RAND = new Random();

    private final MessageBus bus; 

    public Producer(MessageBus bus) {
        this.bus = bus;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            String topic = TOPICS[RAND.nextInt(TOPICS.length)];
            String payload = "Message " + RAND.nextInt(1000);
            bus.postMessage(new Message(topic, payload));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
