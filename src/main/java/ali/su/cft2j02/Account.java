package ali.su.cft2j02;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private String holderName;
    private final Map<Currency, Long> balance = new HashMap<>();

    public Account(String holderName) {
        holderNameChecks(holderName);
        this.holderName = holderName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        holderNameChecks(holderName);
        this.holderName = holderName;
    }

    public Map<Currency, Long> getBalance() {
        return Map.copyOf(balance);
    }

    public Long getBalance(Currency currency) {
        return balance.get(currency);
    }

    public void setBalance(Currency currency, Long balance) {
        if (balance < 0) throw new IllegalArgumentException("Balance cannot be less than zero.");
        this.balance.put(currency, balance);
    }

    private void holderNameChecks(String holderName) {
        if (holderName == null || holderName.isEmpty()) throw new IllegalArgumentException("Account holder name cannot be empty.");
    }
}
