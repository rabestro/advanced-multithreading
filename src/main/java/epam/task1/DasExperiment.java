package epam.task1;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

public class DasExperiment {

    private static final int TEST_DURATION_MS = 10000;

    public static void main(String[] args) {

        System.out.println("Testing with HashMap:");
        testMap(new HashMap<>());

        System.out.println("\nTesting with ConcurrentHashMap:");
        testMap(new ConcurrentHashMap<>());

        System.out.println("\nTesting with Collections.synchronizedMap:");
        testMap(Collections.synchronizedMap(new HashMap<>()));

        System.out.println("\nTesting with custom synchronized ThreadSafeMap:");
        testMap(new SynchronizedThreadSafeMap<>());

        System.out.println("\nTesting with custom unsynchronized ThreadSafeMap:");
        testMap(new UnsynchronizedThreadSafeMap<>());
    }

    public static void testMap(Map<Integer, Integer> map) {
        // Thread to add elements to the map
        Thread addElementsThread = new Thread(() -> {
            try {
                int i = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    map.put(i, i);
                    i++;

                    // Sleep for a while to simulate work
                    TimeUnit.MILLISECONDS.sleep(50);
                }
            } catch (InterruptedException e) {
                System.out.println("Adding elements thread interrupted.");
            }
        });

        // Thread to sum up the values in the map
        Thread sumValuesThread = new Thread(() -> {
            try {
                AtomicInteger sum = new AtomicInteger();
                while (!Thread.currentThread().isInterrupted()) {
                    sum.set(0);
                    try {
                        synchronized (map) {
                            map.forEach((key, value) -> sum.addAndGet(value));
                        }
                    } catch (ConcurrentModificationException e) {
                        System.out.println("ConcurrentModificationException caught.");
                        break;
                    }

                    System.out.println("Current sum: " + sum);

                    // Sleep for a while to simulate work
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Summing values thread interrupted.");
            }
        });

        addElementsThread.start();
        sumValuesThread.start();

        // Let the threads run for a few seconds, then interrupt them
        try {
            TimeUnit.MILLISECONDS.sleep(TEST_DURATION_MS);
        } catch (InterruptedException e) {
            // If the main thread is interrupted, just exit
        }
        addElementsThread.interrupt();
        sumValuesThread.interrupt();
    }

    // Custom thread-safe maps
    public static class SynchronizedThreadSafeMap<K, V> extends HashMap<K, V> {
        public synchronized V put(K key, V value) {
            return super.put(key, value);
        }

        public synchronized void forEach(BiConsumer<? super K, ? super V> action) {
            super.forEach(action);
        }
    }

    public static class UnsynchronizedThreadSafeMap<K, V> extends HashMap<K, V> {}
}
