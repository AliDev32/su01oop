package ali.su.cft2j02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ali.su.cft2j02.Currency.*;
import static org.junit.jupiter.api.Assertions.*;

public class SaveTest {
    @Test
    @DisplayName("Save immutability")
    void saveImmutability() {
        SaveKeeper saveKeeper = new SaveKeeper();
        Account acc = new Account("accHolderName");
        acc.setBalance(RUB, 1L);
        saveKeeper.putSave(acc.save("Save#1"));

        acc.setHolderName("accHolderNameChanged");
        acc.setBalance(CAT, 1L);

        SaveAccount save = (SaveAccount) saveKeeper.getSave("Save#1");

        assertNotEquals(save.getHolderName(), acc.getHolderName());
        assertNotEquals(save.getBalance(), acc.getBalance());
    }

    @Test
    @DisplayName("Restore works properly")
    void testRestore() {
        SaveKeeper saveKeeper = new SaveKeeper();
        String originalAccName = "accHolderName";
        Account acc = new Account(originalAccName);
        acc.setBalance(RUB, 1L);
        var originalBalance = acc.getBalance();

        saveKeeper.putSave(acc.save("Save#1"));

        acc.setHolderName("accHolderNameChanged");
        acc.setBalance(RUB, 2L);
        acc.setBalance(CAT, 2L);

        acc.restore((SaveAccount) saveKeeper.getSave("Save#1"));

        assertEquals(originalAccName, acc.getHolderName());
        assertEquals(originalBalance, acc.getBalance());
    }
}
