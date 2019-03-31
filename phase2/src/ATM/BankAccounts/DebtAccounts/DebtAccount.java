package ATM.BankAccounts.DebtAccounts;
import ATM.BankAccounts.BankAccount;
import java.util.Date;


/**
 * An abstract DebtAccount class.
 */
public abstract class DebtAccount extends BankAccount {

    private double MAX_DEBT = 3000;

    DebtAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    public double getMaxDebt() {
        return this.MAX_DEBT;
    }

    public boolean setMaxDebt(double newMaxDebt) {
        if (newMaxDebt > 0) {
            this.MAX_DEBT = newMaxDebt;
            return true;
        } else {
            return false;
        }
    }

    public boolean deposit(double amount) {
        this.balance -= amount;
        return true;
    }
}
