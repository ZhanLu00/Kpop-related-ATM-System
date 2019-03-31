package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.BankAccount;
import java.util.Date;


/**
 * An abstract DebtAccount class.
 */
public abstract class DebtAccount extends BankAccount {

    final double MAX_DEBT = 420;

    DebtAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    public boolean deposit(double amount) {
        this.balance -= amount;
        return true;
    }
}
