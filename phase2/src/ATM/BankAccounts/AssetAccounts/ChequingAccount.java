package ATM.BankAccounts.AssetAccounts;
import java.util.Date;


/**
 * A ChequingAccount class.
 */
public class ChequingAccount extends AssetAccount {

    public ChequingAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    /**
     * Attempts to withdraw *amount* from account.
     * Succeeds if balance after withdrawal >= -100.
     */
    public boolean withdraw(double amount) {
        if (this.balance - amount >= -100) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

}
