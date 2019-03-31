package ATM.BankAccounts.AssetAccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ChequingAccountTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testDeposit() {

       Date dt = new Date(2019, 3, 25);


       ChequingAccount acc = new ChequingAccount(dt, 200);

       assertEquals(acc.getBalance(), 200);


    }

    @After
    public void tearDown() throws Exception {
    }
}