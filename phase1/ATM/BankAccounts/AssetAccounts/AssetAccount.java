package src.ATM.BankAccounts.AssetAccounts;
import src.ATM.BankAccounts.BankAccount;
import src.ATM.Transaction;

import java.util.Date;


/**
 * An abstract AssetAccount class.
 */
public abstract class AssetAccount extends BankAccount {

    protected AssetAccount(Date date, double balance, Transaction lastTransaction) {
        super(date, balance,lastTransaction);
    }

    protected AssetAccount(Date date, double balance) {
        super( date, balance);
    }

    public double getBalance() {
        return this.balance;
    }

}
