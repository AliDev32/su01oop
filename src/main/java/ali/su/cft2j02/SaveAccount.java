package ali.su.cft2j02;

import java.util.HashMap;
import java.util.Map;

public class SaveAccount implements Save<Account> {
    private final String description;
    private final String holderName;
    private final Map<Currency, Long> balance;

    public SaveAccount(String description, Account acc) {
        this.description = description;
        this.holderName = acc.getHolderName();
        this.balance = acc.getBalance();
    }

    @Override
    public String getSaveDescription() {
        return description;
    }

    public String getHolderName() {
        return holderName;
    }

    public Map<Currency, Long> getBalance() {
        return new HashMap<>(balance);
    }

    @Override
    public String toString() {
        return "SaveAccount{" +
                "description='" + description + '\'' +
                ", holderName='" + holderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
