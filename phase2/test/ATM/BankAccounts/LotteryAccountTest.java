package ATM.BankAccounts;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ATM.BankAccounts.ExtraAccounts.LotteryAccount;

import static org.junit.Assert.*;

public class LotteryAccountTest {
    ATM.BankAccounts.ExtraAccounts.LotteryAccount acc;
    Date dt;

    @Before
    public void setUp(){
        dt = new Date(2019, 3, 25);
        acc = new LotteryAccount(dt, 400);
    }

    @Test
    public void testDeposit() {
        assertEquals(acc.getBalance(), 400);
        acc.deposit(100);
        assertEquals(acc.getBalance(), 500);
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
        for (int i = 0; i < 100; i++) {
            acc.balance = 400;
            acc.collectInterest();
            assertTrue(bal*0.5 <= acc.getBalance() <= bal*1.5);
        }
    }
}
