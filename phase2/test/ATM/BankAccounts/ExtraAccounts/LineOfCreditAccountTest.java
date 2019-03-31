package ATM.BankAccounts.ExtraAccounts;

import ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LineOfCreditAccountTest {
    LineOfCreditAccount acc;
    Date dt;

    @Before
    public void setUp(){
        dt = new Date(2019, 3, 25);
        acc = new LineOfCreditAccount(dt, 400);
    }

    @Test
    public void testDeposit() {
        assertEquals(acc.getBalance(), 400);
        acc.deposit(100);
        assertEquals(acc.getBalance(), 300);
    }

    @Test
    public void testWithdraw(){
        assertFalse(acc.withdraw(21));
        assertTrue(acc.withdraw(20));
        assertEquals(acc.getBalance(), 420);
    }
}
