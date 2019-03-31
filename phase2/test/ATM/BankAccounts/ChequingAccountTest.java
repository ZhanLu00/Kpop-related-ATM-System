package ATM.BankAccounts.AssetAccounts;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ATM.BankAccounts.AssetAccounts.ChequingAccount;

import static org.junit.Assert.*;

public class ChequingAccountTest {
    ATM.BankAccounts.AssetAccounts.ChequingAccount acc;
    Date dt;

    @Before
    public void setUp(){
        dt = new Date(2019, 3, 25);
        acc = new ChequingAccount(dt, 400);
    }

    @Test
    public void testDeposit() {
        assertEquals(acc.getBalance(), 400);
        acc.deposit(100);
        assertEquals(acc.getBalance(), 500);
    }

    @Test
    public void testWithdraw() {
        assertFalse(acc.withdraw(600));
        assertEquals(acc.getBalance(), 400);
        assertTrue(acc.withdraw(500));
        assertEquals(acc.getBalance(), -100);
    }

}