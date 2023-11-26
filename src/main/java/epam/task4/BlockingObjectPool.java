package epam.task4;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingObjectPool {

    private final LinkedBlockingQueue<Object> pool;

    public BlockingObjectPool(int size) {
        pool = new LinkedBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            pool.offer(new Object());
        }
    }

    public Object get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            // Restore the interrupted status of the thread after handling the InterruptedException
            Thread.currentThread().interrupt();
            // We return null here as an indication that the get operation was interrupted;
            // this should be handled by the caller accordingly (e.g., by retrying or abandoning the operation)
            return null;
        }
    }

    public void take(Object object) {
        try {
            pool.put(object);
        } catch (InterruptedException e) {
            // Restore the interrupted status of the thread after handling the InterruptedException
            Thread.currentThread().interrupt();
        }
    }
}
