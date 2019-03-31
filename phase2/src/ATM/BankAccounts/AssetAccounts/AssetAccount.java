package src.ATM.BankAccounts.AssetAccounts;
import src.ATM.BankAccounts.BankAccount;

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
