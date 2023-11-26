package epam.task5;

import java.math.BigDecimal;

public class ExchangeService {
    private final UserDao userDao;

    public ExchangeService(UserDao userDao) {
        this.userDao = userDao;
    }

    public synchronized void exchange(int userId, Currency fromCurrency, Currency toCurrency, BigDecimal amount, BigDecimal rate) {
        // Load account, do the conversion, and save the account
    }
}
