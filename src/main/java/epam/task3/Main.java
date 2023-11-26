package epam.task3;

public class Main {

    public static void main(String[] args) {
        MessageBus bus = new MessageBus();

        Producer producer1 = new Producer(bus);
        Producer producer2 = new Producer(bus);

        Consumer consumer1 = new Consumer(bus, "topic1");
        Consumer consumer2 = new Consumer(bus, "topic2");
        Consumer consumer3 = new Consumer(bus, "topic3");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        producer1.interrupt();
        producer2.interrupt();
        consumer1.interrupt();
        consumer2.interrupt();
        consumer3.interrupt();
    }
}
