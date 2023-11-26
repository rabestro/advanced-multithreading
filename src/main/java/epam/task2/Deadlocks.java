package epam.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deadlocks {
    private static final List<Integer> numbers = new ArrayList<>();
    private static final Object numbersLock = new Object();
    private static final Random random = new Random();

    public static void main(String[] args) {
        // 1st thread: infinitely write random numbers to the collection
        Thread writerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (numbersLock) {
                    numbers.add(random.nextInt(100));
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println("Writer thread interrupted.");
                    break;
                }
            }
        });

        // 2nd thread: print the sum of the numbers in the collection
        Thread sumThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                int sum = 0;
                synchronized (numbersLock) {
                    for (int number : numbers) {
                        sum += number;
                    }
                }

                System.out.println("Sum: " + sum);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Sum thread interrupted.");
                    break;
                }
            }
        });

        // 3rd thread: print the square root of the sum of squares of all numbers in the collection
        Thread sqrtThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                double sumOfSquares = 0;
                synchronized (numbersLock) {
                    for (int number : numbers) {
                        sumOfSquares += Math.pow(number, 2);
                    }
                }

                System.out.println("Sqrt of sum of squares: " + Math.sqrt(sumOfSquares));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Sqrt thread interrupted.");
                    break;
                }
            }
        });

        writerThread.start();
        sumThread.start();
        sqrtThread.start();

        // Run threads for 10 seconds and then interrupt them to stop
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // If the main thread is interrupted, just exit
        }
        
        writerThread.interrupt();
        sumThread.interrupt();
        sqrtThread.interrupt();
        System.out.println("Exiting main thread.");
    }
}
