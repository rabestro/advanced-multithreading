package epam.task6;

import java.util.concurrent.*;

public class ConcurrencyModel {
    private static final int LIMIT = 10;
    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(LIMIT);

    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < LIMIT; i++) {
                try {
                    produce(i);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < LIMIT; i++) {
                try {
                    consume();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }

    private static void produce(int i) throws InterruptedException {
        queue.put(i);
    }

    private static void consume() throws InterruptedException {
        int data = queue.take();
        System.out.println("Data consumed: " + data);
    }
}
