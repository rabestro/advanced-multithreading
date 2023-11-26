package epam.task5;

import java.math.BigDecimal;

public class ExchangeRate {
    private final Currency fromCurrency;
    private final Currency toCurrency;
    private final BigDecimal rate;

    public ExchangeRate(Currency fromCurrency, Currency toCurrency, BigDecimal rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
