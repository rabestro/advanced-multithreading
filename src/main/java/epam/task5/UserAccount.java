package epam.task5;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserAccount {
    private final int userId;
    private final Map<Currency, BigDecimal> balances;

    public UserAccount(int userId) {
        this.userId = userId;
        this.balances = new ConcurrentHashMap<>();
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getBalance(Currency currency) {
        return balances.getOrDefault(currency, BigDecimal.ZERO);
    }

    public void setBalance(Currency currency, BigDecimal amount) {
        balances.put(currency, amount);
    }
}
