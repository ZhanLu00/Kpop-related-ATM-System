package ATM.BankAccounts.AssetAccounts;
import ATM.BankAccounts.BankAccount;
import ATM.Transaction;

import java.util.Date;


/**
 * An abstract AssetAccount class.
 */
public abstract class AssetAccount extends BankAccount {


    protected AssetAccount(Date date, double balance) {
        super( date, balance);
    }

    public double getBalance() {
        return this.balance;
    }

}
