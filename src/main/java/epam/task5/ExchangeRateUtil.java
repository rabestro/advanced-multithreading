package epam.task5;

import java.math.BigDecimal;

public class ExchangeRateUtil {
    public static BigDecimal convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate);
    }
}
