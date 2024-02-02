package ali.su.cft2j02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    @DisplayName("Invalid account holder name throws exception")
    void setHolderNameInvalid() {
        String nullHolderName = null;
        String emptyHolderName = "";

        assertThrows(IllegalArgumentException.class, () -> new Account(nullHolderName));
        assertThrows(IllegalArgumentException.class, () -> new Account(emptyHolderName));

        var acc = new Account("AccName");

        assertThrows(IllegalArgumentException.class, () -> acc.setHolderName(nullHolderName));
        assertThrows(IllegalArgumentException.class, () -> acc.setHolderName(emptyHolderName));
    }

    @Test
    @DisplayName("Correct account holder name change")
    void setHolderNameValid() {
        var acc = new Account("HolderName");
        var newHolderName = "NewHolderName";
        acc.setHolderName(newHolderName);
        assertEquals(newHolderName, acc.getHolderName());
    }

    @Test
    @DisplayName("Invalid balance value throws exception")
    void setBalanceInvalid() {
        var acc = new Account("AccName");
        assertThrows(IllegalArgumentException.class, () -> acc.setBalance(Currency.RUB, -1));
    }

    @Test
    @DisplayName("Checking for correct balance encapsulation")
    void balanceEncapsulationValid() {
        var acc = new Account("AccName");
        acc.setBalance(Currency.RUB, 3);

        final var balanceCopy = acc.getBalance();

        assertThrows(UnsupportedOperationException.class, () -> balanceCopy.put(Currency.RUB, 8L));

        acc.setBalance(Currency.RUB, 1);

        assertNotEquals(balanceCopy, acc.getBalance());
        assertNotEquals(balanceCopy.get(Currency.RUB), acc.getBalance(Currency.RUB));
    }

}