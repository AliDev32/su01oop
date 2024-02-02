package ali.su.cft2j02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ali.su.cft2j02.Currency.*;
import static org.junit.jupiter.api.Assertions.*;

public class SaveTest {
    @Test
    @DisplayName("Save immutability")
    void saveImmutability() {
        var saveKeeper = new SaveKeeper();
        var acc = new Account("accHolderName");
        acc.setBalance(RUB, 1);
        saveKeeper.putSave(acc.save("Save#1"));

        acc.setHolderName("accHolderNameChanged");
        acc.setBalance(CAT, 1);

        var save = (SaveAccount) saveKeeper.getSave("Save#1");

        assertNotEquals(save.getHolderName(), acc.getHolderName());
        assertNotEquals(save.getBalance(), acc.getBalance());
    }

    @Test
    @DisplayName("Restore works properly")
    void testRestore() {
        var saveKeeper = new SaveKeeper();
        var originalAccName = "accHolderName";
        var acc = new Account(originalAccName);
        acc.setBalance(RUB, 1);
        var originalBalance = acc.getBalance();

        saveKeeper.putSave(acc.save("Save#1"));

        acc.setHolderName("accHolderNameChanged");
        acc.setBalance(RUB, 2);
        acc.setBalance(CAT, 2);

        acc.restore((SaveAccount) saveKeeper.getSave("Save#1"));

        assertEquals(originalAccName, acc.getHolderName());
        assertEquals(originalBalance, acc.getBalance());
    }

    @Test
    @DisplayName("Restore with account creation")
    void testRestoreNewAcc() {
        var saveKeeper = new SaveKeeper();
        var originalAccName = "accHolderName";
        var acc = new Account(originalAccName);
        acc.setBalance(RUB, 1);

        saveKeeper.putSave(acc.save("Save#1"));

        var newAcc = new Account((SaveAccount) saveKeeper.getSave("Save#1"));

        assertEquals(originalAccName, newAcc.getHolderName());
        assertEquals(acc.getBalance(), newAcc.getBalance());
    }
}
