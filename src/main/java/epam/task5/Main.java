package epam.task5;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        UserDao userDao = new UserDao();
        ExchangeService exchangeService = new ExchangeService(userDao);

        UserAccount user1 = new UserAccount(1);

        // Sample account and currencies
        Currency dollar = new Currency("USD");
        Currency euro = new Currency("EUR");
        user1.setBalance(dollar, new BigDecimal("1000"));
        user1.setBalance(euro, new BigDecimal("0"));

        // Set sample exchange rate
        BigDecimal exchangeRate = new BigDecimal("0.85");

        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Simultaneous currency exchanges for single account by multiple threads
        Runnable exchangeTask = () -> exchangeService.exchange(user1.getUserId(), dollar, euro, new BigDecimal("100"), exchangeRate);

        for (int i = 0; i < 10; i++) {
            executor.submit(exchangeTask);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        // Save user account after all exchanges
        try {
            userDao.saveAccount(user1);
        } catch (IOException e) {
            System.out.println("Error saving user account: " + e.getMessage());
        }
    }
}
