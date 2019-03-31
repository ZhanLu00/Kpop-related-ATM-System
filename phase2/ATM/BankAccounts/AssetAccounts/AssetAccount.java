package ATM.BankAccounts.AssetAccounts;
import ATM.BankAccounts.BankAccount;
import ATM.Transaction;

import java.util.Date;


/**
 * An abstract AssetAccount class.
 */
public abstract class AssetAccount extends BankAccount {

    public AssetAccount(Date date, double initialBalance) {
        super(date, initialBalance);
    }

    public boolean deposit(double amount) {
        this.balance += amount;
        return true;
    }

}
