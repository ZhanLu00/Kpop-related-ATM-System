package ATM.BankAccounts.AssetAccounts;
import java.util.Date;


/**
 * A SavingsAccount class.
 */
public class SavingsAccount extends AssetAccount {

    public SavingsAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    /**
     * Attempts to withdraw *amount* from account.
     * Succeeds if balance >= amount.
     */
    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Increases balance by 0.1%.
     */
    public void collectInterest() {
        this.balance *= 1.001;
    }

}
