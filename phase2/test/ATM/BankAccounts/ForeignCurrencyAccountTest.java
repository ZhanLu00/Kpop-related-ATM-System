package test.ATM.BankAccounts;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ATM.BankAccounts.ExtraAccounts.ForeignCurrencyAccount;

import static org.junit.Assert.*;

public class ForeignCurrencyAccountTest {
    ForeignCurrencyAccount acc;
    Date dt;

    @Before
    public void setUp(){
        dt = new Date(2019, 3, 25);
        acc = new ForeignCurrencyAccount(dt, 400, 0.5);
    }

    @Test
    public void testDeposit() {
        assertEquals(acc.getBalance(), 400);
        assertEquals(acc.getForeignCurrencyBalance(), 200);
        acc.deposit(100);
        assertEquals(acc.getBalance(), 500);
        assertEquals(acc.getForeignCurrencyBalance(), 250);
    }

    @Test
    public void testWithdraw() {
        assertFalse(acc.withdraw(400.01));
        assertEquals(acc.getBalance(), 400);
        assertEquals(acc.getForeignCurrencyBalance(), 200);
        assertTrue(acc.withdraw(400));
        assertEquals(acc.getBalance(), 0);
        assertEquals(acc.getForeignCurrencyBalance(), 0);
    }

}
