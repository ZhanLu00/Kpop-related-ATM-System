package ATM.BankAccounts.AssetAccounts;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;


public class ChequingAccountTest {

    Date date;
    ChequingAccount acc;

    @Before
    public void setUp() {
        date = new Date(2019, 3, 25);
        acc = new ChequingAccount(date, 500);
    }

    @Test
    public void testBalance() {
        assertEquals(500, acc.getBalance(), 0.1);
    }

    @Test
    public void testDeposit() {
        assertTrue(acc.deposit(500));
        assertEquals(1000, acc.getBalance(), 0.1);
    }

    @Test
    public void testWithdraw() {

        assertTrue(acc.withdraw(300));
        assertEquals(200, acc.getBalance(), 0.1);

        assertTrue(!acc.withdraw(500));
        assertEquals(200, acc.getBalance(), 0.1);

        assertTrue(acc.withdraw(300));
        assertEquals(acc.getBalance(), -100, 0.1);

    }

    @Test
    public void testDate() {
        assertTrue(date.equals(acc.getDateCreated()));
    }

}