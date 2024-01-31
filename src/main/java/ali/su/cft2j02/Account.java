package ali.su.cft2j02;

import java.util.*;

public class Account {
    private String holderName;
    private final Map<Currency, Long> balance = new HashMap<>();
    private final Deque<Command> commandHistory = new ArrayDeque<>();

    public Account(String holderName) {
        holderNameChecks(holderName);
        this.holderName = holderName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        holderNameChecks(holderName);
        var redoHolderName = this.holderName;
        commandHistory.addLast(() -> this.holderName = redoHolderName);
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
        if (this.balance.containsKey(currency)) {
            var redoBalance = this.balance.get(currency);
            commandHistory.addLast(() -> this.balance.put(currency, redoBalance));
        } else {
            commandHistory.addLast(() -> this.balance.remove(currency));
        }
        this.balance.put(currency, balance);
    }

    private void holderNameChecks(String holderName) {
        if (holderName == null || holderName.isEmpty()) throw new IllegalArgumentException("Account holder name cannot be empty.");
    }

    public void undo() {
        if (commandHistory.isEmpty()) throw new IllegalStateException("No actions for undo.");
        //if (!commandHistory.isEmpty())
        commandHistory.pollLast().execute();
    }

    public boolean canUndo() {
        return !commandHistory.isEmpty();
    }
}
