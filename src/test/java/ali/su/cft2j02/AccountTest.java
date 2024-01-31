package ali.su.cft2j02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    @DisplayName("Invalid account holder name throws exception")
    void setHolderNameInvalid() {
        String nullHolderName = null;
        String emptyHolderName = "";

        assertThrows(IllegalArgumentException.class, () -> new Account(nullHolderName));
        assertThrows(IllegalArgumentException.class, () -> new Account(emptyHolderName));

        Account acc = new Account("AccName");

        assertThrows(IllegalArgumentException.class, () -> acc.setHolderName(nullHolderName));
        assertThrows(IllegalArgumentException.class, () -> acc.setHolderName(emptyHolderName));
    }

    @Test
    @DisplayName("Correct account holder name change")
    void setHolderNameValid() {
        Account acc = new Account("HolderName");
        String newHolderName = "NewHolderName";
        acc.setHolderName(newHolderName);
        assertEquals(newHolderName, acc.getHolderName());
    }

    @Test
    @DisplayName("Invalid balance value throws exception")
    void setBalanceInvalid() {
        Account acc = new Account("AccName");
        assertThrows(IllegalArgumentException.class, () -> acc.setBalance(Currency.RUB, -1L));
    }

    @Test
    @DisplayName("Checking for correct balance encapsulation")
    void balanceEncapsulationValid() {
        Account acc = new Account("AccName");
        acc.setBalance(Currency.RUB, 3L);

        final Map<Currency, Long> balanceCopy = acc.getBalance();

        assertThrows(UnsupportedOperationException.class, () -> balanceCopy.put(Currency.RUB, 8L));

        acc.setBalance(Currency.RUB, 1L);

        assertNotEquals(balanceCopy, acc.getBalance());
        assertNotEquals(balanceCopy.get(Currency.RUB), acc.getBalance(Currency.RUB));
    }

}