package test.ATM.BankAccounts;

import ATM.BankAccounts.AssetAccounts.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class SavingsAccountTest {
    Date dt;
    SavingsAccount acc;
    @Before
    public void setUp(){
        dt = new Date(2019, 3, 25);
        acc = new SavingsAccount(dt, 400);
    }

    @Test
    public void testDeposit() {
        assertTrue(acc.getBalance() == 400);
        acc.deposit(100);
        assertTrue(acc.getBalance() == 500);
    }

    @Test
    public void testWithdraw(){
        assertFalse(acc.withdraw(400.01));
        assertEquals(acc.getBalance(), 400);
        assertTrue(acc.withdraw(400));
        assertEquals(acc.getBalance(), 0);
    }

    @Test
    public void testCollectInterest(){
        for (int i = 0; i < 5; i++) {
            acc.collectInterest();
        }
        assertEquals(acc.getBalance(), 400*Math.pow(1.001, 5));
    }
}
