package epam.task6;

import java.util.*;

public class CClassicModel {

    private static final LinkedList<Integer> queue = new LinkedList<>();
    private static final int LIMIT = 10;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < LIMIT; i++) {
                produce(i);
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < LIMIT; i++) {
                consume();
            }
        });

        producerThread.start();
        consumerThread.start();
    }

    private static void produce(int i) {
        synchronized (lock) {
            queue.add(i);
            lock.notify();
        }
    }

    private static void consume() {
        synchronized (lock) {
            while (queue.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int data = queue.removeFirst();
            System.out.println("Data consumed: " + data);
        }
    }
}
