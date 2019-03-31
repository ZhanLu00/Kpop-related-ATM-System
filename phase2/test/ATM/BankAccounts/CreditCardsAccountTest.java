package test.ATM.BankAccounts;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import ATM.BankAccounts.DebtAccounts.CreditCardsAccount;
public class CreditCardsAccountTest {
    CreditCardsAccount acc;
    Date dt;

    @Before
    public void setUp(){
        dt = new Date(2019, 3, 25);
        acc = new CreditCardsAccount(dt, 400);
    }

    @Test
    public void testDeposit() {
        assertEquals(acc.getBalance(), 400);
        acc.deposit(100);
        assertEquals(acc.getBalance(), 300);
        acc.deposit(400);
        assertEquals(acc.getBalance(), -100);
    }
}
