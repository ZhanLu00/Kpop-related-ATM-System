package ATM.BankAccounts.AssetAccounts;
import ATM.BankAccounts.BankAccount;
import java.util.Date;


/**
 * An abstract AssetAccount class.
 */
public abstract class AssetAccount extends BankAccount {

    public AssetAccount(Date dateCreated, double initialBalance) {
        super(dateCreated, initialBalance);
    }

    public boolean deposit(double amount) {
        this.balance += amount;
        return true;
    }

}
