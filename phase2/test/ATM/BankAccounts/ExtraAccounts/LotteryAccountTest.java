package ATM.BankAccounts.ExtraAccounts;
import ATM.BankAccounts.DebtAccounts.LineOfCreditAccount;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;


public class LotteryAccountTest {

    Date date;
    LotteryAccount acc;

    @Before
    public void setUp() {
        date = new Date(2019, 3, 25);
        acc = new LotteryAccount(date, 500);
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

        assertTrue(acc.withdraw(300));
        assertEquals(800, acc.getBalance(), 0.01);

        assertTrue(!acc.withdraw(acc.getBalance() + acc.getMaxDebt() + 100));
        assertEquals(800, acc.getBalance(), 0.01);

        assertTrue(acc.withdraw(acc.getMaxDebt() - acc.getBalance()));
        assertEquals(acc.getMaxDebt(), acc.getBalance(), 0.01);

    }

    @Test
    public void testDate() {
        assertTrue(date.equals(acc.getDateCreated()));
    }

    @Test
    public void testPlay() {
        for (int i=1; i<=500; i++) {
            double oldBalance = acc.getBalance();
            acc.play();
            double newBalance = acc.getBalance();
            assertEquals(oldBalance, newBalance, oldBalance * 1.5);
            if (Math.abs(newBalance - oldBalance) > Math.abs(oldBalance) * 0.4) {
                return;
            }
        }
        fail();
    }

}