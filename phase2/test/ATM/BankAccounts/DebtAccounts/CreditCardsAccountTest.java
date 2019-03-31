package ATM.BankAccounts.DebtAccounts;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;


public class CreditCardsAccountTest {

    Date date;
    CreditCardsAccount acc;

    @Before
    public void setUp() {
        date = new Date(2019, 3, 25);
        acc = new CreditCardsAccount(date, 500);
    }

    @Test
    public void testBalance() {
        assertEquals(500, acc.getBalance(), 0.01);
    }

    @Test
    public void testDeposit() {
        assertTrue(acc.deposit(500));
        assertEquals(0, acc.getBalance(), 0.01);
    }

    @Test
    public void testWithdraw() {

        assertTrue(!acc.withdraw(300));
        assertEquals(500, acc.getBalance(), 0.01);

    }

    @Test
    public void testDate() {
        assertTrue(date.equals(acc.getDateCreated()));
    }

}