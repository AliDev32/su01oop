package ali.su.cft2j02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ali.su.cft2j02.Currency.*;
import static org.junit.jupiter.api.Assertions.*;

class UndoTest {
    @Test
    @DisplayName("Correct result for multiple undo calls")
    void setHolderNameInvalid() {
        Account acc = new Account("AccName");
        var originalBalance = acc.getBalance();

        acc.setBalance(RUB, 100L);
        acc.setHolderName("Василий Иванов");
        acc.setBalance(RUB, 300L);

        assertEquals(300L, acc.getBalance(RUB));
        assertEquals("Василий Иванов", acc.getHolderName());
        acc.undo();
        assertEquals(100L, acc.getBalance(RUB));
        assertEquals("Василий Иванов", acc.getHolderName());
        acc.undo();
        assertEquals(100L, acc.getBalance(RUB));
        assertEquals("AccName", acc.getHolderName());
        acc.undo();
        assertEquals(originalBalance, acc.getBalance());
        assertEquals("AccName", acc.getHolderName());
    }

    @Test
    @DisplayName("Check undo possibility")
    void canUndo() {
        Account acc = new Account("AccName");

        acc.setBalance(RUB, 108L);
        assertTrue(acc.canUndo());

        acc.undo();
        assertFalse(acc.canUndo());
    }

    @Test
    @DisplayName("Error appear when no possible undo")
    void errorOnEmptyUndo() {
        Account acc = new Account("AccName");
        assertThrows(IllegalStateException.class, acc::undo);
    }
}