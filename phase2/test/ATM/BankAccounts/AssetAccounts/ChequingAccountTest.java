package ATM.BankAccounts.AssetAccounts;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ChequingAccountTest {

    @Test
    public void test() {
        Date dt = new Date(2019, 3, 25);

        ChequingAccount acc = new ChequingAccount(dt, 400);
        assertTrue(acc.getBalance() == 400);
        acc.deposit(100);
        assertTrue(acc.getBalance() == 500);
    }

}