package ATM.BankAccounts.AssetAccounts;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;


public class SavingsAccountTest {

    Date date;
    SavingsAccount acc;

    @Before
    public void setUp() {
        date = new Date(2019, 3, 25);
        acc = new SavingsAccount(date, 500);
    }

    @Test
    public void testBalance() {
        assertEquals(500, acc.getBalance(), 0.01);
    }

    @Test
    public void testDeposit() {
        assertTrue(acc.deposit(500));
        assertEquals(1000, acc.getBalance(), 0.01);
    }

    @Test
    public void testWithdraw() {

        assertTrue(acc.withdraw(300));
        assertEquals(200, acc.getBalance(), 0.01);

        assertTrue(!acc.withdraw(500));
        assertEquals(200, acc.getBalance(), 0.01);

        assertTrue(acc.withdraw(200));
        assertEquals(acc.getBalance(), 0, 0.01);

    }

    @Test
    public void testDate() {
        assertTrue(date.equals(acc.getDateCreated()));
    }

    @Test
    public void testCollectInterest() {
        for (int i=1; i<=500; i++) {
            acc.collectInterest();
            assertEquals(500*Math.pow(1.001, i), acc.getBalance(), 0.01);
        }
    }

}